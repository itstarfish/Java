import IMachine.IMachine;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloWorld
{
    public static void main (String[] arg) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        ContinuousPrinter cp = new ContinuousPrinter();
        //Thread thread = new Thread(cp);
        //thread.start();

        ExecutorService executor = Executors.newFixedThreadPool(100);
        executor.submit(cp);
        executor.submit(cp);
        executor.submit(cp);
        executor.submit(cp);
        executor.submit(cp);
        executor.submit(cp);
        executor.shutdown();


        for (int i = 0; i<100; i++){
            System.out.println("Main Thread " + i);
        }

        /*
        Printer<ColorCartridge> printer = new Printer<ColorCartridge>(true, "FGH", ColorCartridge.RED);



        for (ColorCartridge cartridge : ColorCartridge.values()){
            System.out.println(cartridge.printColor());
        }

        printer.loadPaper(5);

        PrintingDevice annotation = printer.getClass().getAnnotation(PrintingDevice.class);
        Method printMethod = printer.getClass().getMethod(annotation.defaultPrintMethod(), int.class);

        printMethod.invoke(printer, annotation.defaultNumberOfCopies());
        //printer.print(3);



        printer.outputPage(4);
        */
        //Path path = Paths.get("newfile.txt");

        //Create a file
        /*try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */
        /*
        //Deleting a file
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        //Moving a file
        /*
        try {
            Files.move(path, Paths.get("movedfile.txt"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         */

        //
        /*
        switch (printer.getCartridge()){
            case RED:
                System.out.println("RED");
                break;
            case BLUE:
                System.out.println("BLUE");
                break;
            case GREEN:
                System.out.println("GREEN");
                break;
            case YELLOW:
                System.out.println("YELLOW");
                break;
        }
        */

        //Set
        /*Set<Integer> mySet = new HashSet<Integer>();
        mySet.add(1);
        mySet.add(2);
        mySet.add(3);
        mySet.add(1);
        System.out.println(mySet.size());*/
        /*
        //Queue
        Queue<String> myQueue = new LinkedList<String>();
        myQueue.offer("a");
        myQueue.offer("b");
        myQueue.offer("c");

        while (myQueue.peek() != null){
            System.out.println(myQueue.poll());
        }
        */
        //Map
        /*
        Map<String, List<Integer>> testScores = new HashMap<String, List<Integer>>();
        List<Integer> joeScores = new ArrayList<Integer>();
        joeScores.add(80);
        joeScores.add(60);
        joeScores.add(97);

        testScores.put("Joe",joeScores);

        List<Integer> amyScores = new ArrayList<Integer>();
        amyScores.add(30);
        amyScores.add(20);
        amyScores.add(37);
        amyScores.add(32);
        testScores.put("Amy",amyScores);

        List<Integer> fredScores = new ArrayList<Integer>();
        fredScores.add(40);
        fredScores.add(50);
        fredScores.add(47);
        testScores.put("Fred",fredScores);

        //printScores("Fred", testScores);
        printScores("Amy", testScores);
        //printScores("Fred", testScores);
        */
        /*
        printer.loadPaper(10);
        printer.print(5);
        printer.outputPage(2);
        //printer.outputPages();*/

    }

    public static void printOne(Printer<? extends ICartridge> printer){
        String fillPercentage = printer.getCartridge().getFiller();
        System.out.println(fillPercentage);
    }

    public static void printScores(String studentName, Map<String, List<Integer>> scoresMap){
        List<Integer> scores = scoresMap.get(studentName);
        for (int score: scores){
            System.out.println(score);
        }
    }
}




