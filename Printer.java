import IMachine.IMachine;

public class Printer<T> implements IMachine
{
    private String modelNumber;
    private boolean isOn;
    private PaperTray paperTray = new PaperTray();
    Machine machine;
    private T cartridge;

    public Printer(boolean isOn, String modelNumber, T cartridge)
    {
        machine = new Machine(isOn);
        this.modelNumber = modelNumber;
        this.cartridge = cartridge;
    }

    public void print(int copies)
    {
        System.out.println(cartridge.toString());
        String onStatus = "";

        if(machine.isOn())
            onStatus = " is On!";
        else
            onStatus = " is Off!";

        String textToPrint = modelNumber + onStatus;

        while (copies > 0 && paperTray.isEmpty()){
            System.out.println(textToPrint);
            copies--;
            paperTray.usePaper();
        }

        if (!paperTray.isEmpty()){
            System.out.println("Load more paper");
        }
    }

    @Override
    public void TurnOn() {
        System.out.println("Warming up Printer");
        machine.TurnOn();
    }

    @Override
    public void TurnOff() {
        machine.TurnOff();
    }

    @Override
    public boolean isOn() {
        return machine.isOn();
    }

    public <U extends ICartridge> void printUsingCartridge(U cartridge, String message){
        System.out.println(cartridge.getFiller());
        System.out.println(message);
        System.out.println(cartridge.getFiller());
    }

    public String getModelNumber()
    {
        return modelNumber;
    }

    public void loadPaper(int count) {
        paperTray.addPaper(count);
    }

    public T getCartridge() {
        return cartridge;
    }
}
