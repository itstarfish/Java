import IMachine.IMachine;

public class HelloWorld
{
    public static void main (String[] arg)
    {

        Printer<ColorCartridge> printer = new Printer<ColorCartridge>(false, "FGH", new ColorCartridge());
        Printer<BWCartridge> printer2 = new Printer<BWCartridge>(false, "FGH", new BWCartridge());

        printer.printUsingCartridge(new ColorCartridge(),"Hi!");
        printer.printUsingCartridge(new BWCartridge(),"Hi!");
        printOne(printer);

    }

    public static void printOne(Printer<? extends ICartridge> printer){
        String fillPercentage = printer.getCartridge().getFiller();
        System.out.println(fillPercentage);
    }
}




