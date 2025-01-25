import IMachine.IMachine;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PrintingDevice(defaultPrintMethod = "print", defaultNumberOfCopies = 5)
public class Printer<T extends ICartridge> implements IMachine
{
    private String modelNumber;
    private boolean isOn;
    private PaperTray paperTray = new PaperTray();
    Machine machine;
    private T cartridge;
    //private List<Page> pages = new ArrayList<Page>();
    private Map<Integer, Page> pagesMap = new HashMap<Integer, Page>();

    public Printer(boolean isOn, String modelNumber, T cartridge)
    {
        machine = new Machine(isOn);
        this.modelNumber = modelNumber;
        this.cartridge = cartridge;
    }
    public void print(int copies)
    {
        checkCopies(copies);
        int pageNumber = 1;
        String onStatus = "";

        if(machine.isOn())
            onStatus = " is On!";
        else
            onStatus = " is Off!";

        String textToPrint = getTextFromFile();
        //String textToPrint = modelNumber + onStatus;
        //textToPrint += "|||" + cartridge.printColor() +"|||";

        while (copies > 0 && paperTray.isEmpty()){
            //System.out.println(textToPrint);
            //pages.add(new Page(textToPrint));
            pagesMap.put(pageNumber, new Page(textToPrint + ":" + pageNumber));
            copies--;
            pageNumber++;
            paperTray.usePaper();
        }

        if (!paperTray.isEmpty()){
            System.out.println("Load more paper");
        }
    }

    private String getTextFromFile() {
        FileReader reader = null;
        BufferedReader bReader = null;
        CapitalizationReader capReader = null;


        String allText = "";
        try {
            reader = new FileReader("text.txt");
            bReader = new BufferedReader(reader);
            capReader = new CapitalizationReader(bReader);
            String line;
            while((line = capReader.readLine()) != null){
                allText += line +"\n";
            }
            return allText;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally{
            if(capReader != null){
                try {
                    capReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public void outputPage(int pageNumber){
        //System.out.println(pagesMap.get(pageNumber).getText());

        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter("outputpage.txt"));
            writer.println(pagesMap.get(pageNumber).getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally
        {
            if(writer != null){
                writer.close();
            }
        }


    }


    //List output
    /*public void outputPages()
    {
        for (Page currentPage : pages)
        {
            System.out.println(currentPage.getText());
        }
    }*/

    private static void checkCopies(int copies) {
        if(copies < 0)
            throw new IllegalArgumentException("Can't print less than 0 copies");
    }


    @Override
    public void TurnOn() {
        System.out.println("Warming up Printer");
        machine.TurnOn();
    }

    @Override
    public void TurnOff() {
        machine.TurnOff();
        System.out.println("Machine is Off");
    }

    @Override
    public boolean isOn() {
        return machine.isOn();
    }

    public synchronized <U extends ICartridge> void printUsingCartridge(U cartridge, String message){

        System.out.println("Entered: " + Thread.currentThread().threadId());//System.out.println(cartridge.getFiller());
        System.out.println(message);
        //System.out.println(cartridge.getFiller());
        System.out.println("Exited: " +Thread.currentThread().threadId());
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
