import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Loginės operacijos");
        System.out.println("Įrašykite du skaičius");
        logicalOperations(scanner.nextInt(), scanner.nextInt());
/*
        System.out.println("2. Eilučių metodai su StringBuilder");
        System.out.println("Įveskite sakinį");
        scanner.nextLine();
        stringOperation(scanner.nextLine());

        System.out.println("3. Vienmačio masyvo operacijos");
        arrayOperations(scanner);

        System.out.println("4. Dvimatis masyvas su eilutės suma");
        matrixOperations();

        System.out.println("5. Paprastas ciklas");
        printNumbers(scanner);

        System.out.println("6. Skaičių faktorialas");
        calculateFactorial(scanner);


        System.out.println("7. Metodai su parametrais");
        calculate(10,20,true);
        calculate(5,2,false);



        System.out.println("8. Palindromo tikrinimas");
        isPalindrome(scanner.nextLine());


        System.out.println("9. Didžiausia reikšmė dvimačiame masyve");
        findMaxInMatrix();


        System.out.println("10. Naudotojo įvesties analizė");
        analyzeInput(scanner);

         */

    }




    public static void logicalOperations(int one, int two){

        boolean abu = (one % 2 == 0 && two % 2 == 0);
        boolean bentVienas = (one > 0 || two > 0);
        boolean pDidesnisUzAntraIrDalomi = (one > two && one % 5 == 0 && two % 5 == 0);

        System.out.printf("Abu lyginiai: %b.", abu);
        System.out.printf(" Bent vienas teigiamas: %b\n", bentVienas);
        System.out.printf("Pirmas skaičius yra didesnis už antrą ir abu dalomi iš 5: %b\n", pDidesnisUzAntraIrDalomi);
    }

    public static void stringOperation(String sentence){
        System.out.println("Originalus sakinys: " +  sentence);

        StringBuilder capitalCase = new StringBuilder(sentence.toUpperCase());
        System.out.println("Didžiosiomis: " + capitalCase);

        StringBuilder temp = new StringBuilder(sentence);
        String reverse = temp.reverse().toString();
        System.out.println("Atvirkščias: " + reverse);

        String[] words = sentence.split(" ");
        int count = words.length;
        System.out.println("Žodžių skaičius: " + count);

        boolean startJava = sentence.startsWith("Java");
        System.out.println("Prasideda \"Java\": " + startJava);

    }

    public static void arrayOperations(Scanner scanner){
        System.out.println("Įveskite penkis skaičius:");
        int[] numbers = new int[5];
        for (int i = 0; i < 5; i++){
            numbers[i] = scanner.nextInt();
        }

        Arrays.sort(numbers);

        System.out.println("Įveskite ieškomą skaičių:");
        int search = scanner.nextInt();
        int result = Arrays.binarySearch(numbers,search);

        int max = numbers[numbers.length - 1];
        int min = numbers[0];

        int sum = 0;
        for (int number:numbers) {
            sum += number;
        }
        double avg = sum / numbers.length;

        System.out.printf("Didžiausias: %d. Mažiausias: %d. Vidurkis: %.2f.%n", max, min, avg);
        if (result >= 0) {
            System.out.printf("Skaičius %d rastas indekse %d.%n", search, result);
        } else {
            System.out.printf("Skaičius %d nerastas.%n", search);
        }

    }
    public static void matrixOperations(){
        int[][] matrix = new int[3][3];
        Random random = new Random();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                matrix[i][j] = random.nextInt(10) + 1;
            }
        }

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                stringBuilder.append(matrix[i][j]).append(" ");
            }
            stringBuilder.append("\n");
        }
        System.out.println("Sukurta matrica:");
        System.out.println(stringBuilder.toString());

        System.out.println("Skaičiuojamos eilučių sumos:");
        int sum;
        for (int i = 0; i < 3; i++){
            sum = 0;
            for (int j = 0; j < 3; j++){
                sum += matrix[i][j];
            }
            System.out.printf("Eil %d suma: %d.%n", i + 1, sum);
        }

    }

    public static void printNumbers(Scanner scanner){
        System.out.println("Prašome įvesti skaičių n:");
        int n  = scanner.nextInt();

        System.out.printf("Skaičiai nuo 1 iki %d :\n", n);
        for (int i = 1; i <= n; i++){
            System.out.print( i + " ");
        }
        System.out.println();

        System.out.printf("Skaičiai nuo %d iki 1:\n", n);
        for (int i = n; i >= 1; i--){
            System.out.print( i + " ");
        }
        System.out.println();

        int sum = 0;
        for (int i = 1; i <= n; i++){
            sum += i;
        }
        System.out.printf("Visų skaičių suma: %d.%n", sum);

    }

    private static void calculateFactorial(Scanner scanner) {
        System.out.println("Įveskite sveiką skaičių n");
        int n = scanner.nextInt();
        int fact = 1;
        for (int i = n; i >= 1; i--){
            fact *= i;
        }
        System.out.printf("Faktorialas (%d!) = %d.%n", n, fact);
    }

    private static void calculate(int one, int two, boolean add){
        int rez  = 0;
        if (add){
            rez = one + two;
        } else {
            rez = one - two;
        }
        System.out.printf("Rezultatas: %d.%n", rez);
    }

    private static void isPalindrome(String word) {
        StringBuilder reverse = new StringBuilder(word).reverse();
        boolean palindrome = word.equals(reverse.toString());

        System.out.printf("Žodis \"%s\" yra palindromas: %b.%n", word, palindrome);

    }

    public static void findMaxInMatrix(){
        int[][] matrix = new int[4][4];
        Random random = new Random();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                matrix[i][j] = random.nextInt(20) + 1;
            }
        }

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                stringBuilder.append(matrix[i][j]).append(" ");
            }
            stringBuilder.append("\n");
        }
        System.out.println("Sukurta matrica:");
        System.out.println(stringBuilder.toString());

        int max = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if (matrix[i][j] > max){
                    max = matrix[i][j];
                }
            }
        }
        System.out.printf("Didžiausia reikšmė %d", max);

    }

    public static void analyzeInput(Scanner scanner){
        System.out.println("Įveskite sveikus skaičius atskirtus tarpais:");
        String in = scanner.nextLine();

        String[] inArray = in.split(" ");
        int[] numbers = new int[inArray.length];

        for (int i = 0; i < inArray.length; i++){
            numbers[i] = Integer.parseInt(inArray[i]);
        }

        Arrays.sort(numbers);

        System.out.println("Įveskite ieškomą skaičių:");
        int search = scanner.nextInt();
        int rez = Arrays.binarySearch(numbers,search);

        int sum = 0;
        for (int number:numbers) {
            sum += number;
        }
        double avg = sum / numbers.length;

        int min = numbers[0];
        int max = numbers[numbers.length -1];

        System.out.printf("Skaičiai: [%s]. Vidurkis: %.2f. Didžiausia: %d. Mažiausia: %d.%n",
                Arrays.toString(numbers), avg, max, min);
        if (rez >= 0) {
            System.out.printf("Skaičius %d rastas.%n", search);
        } else {
            System.out.printf("Skaičius %d nerastas.%n");
        }

    }
}