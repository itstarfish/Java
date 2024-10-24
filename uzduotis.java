import java.util.Scanner;
public class uzduotis {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Iveskite tekstą: ");
        StringBuilder demoText = new StringBuilder(scanner.nextLine());
        System.out.println(demoText);
        scanner.close();
        int words = 0;
        int symbols = 0;

        char mostCommonSymbol = ' ';
        int mostCommonOccurrence = 0;

        for (int index = 0;index < demoText.length();index++){
            // 2.Simbolių skaičius: Nuskaito tekstą ir paskaičiuoja, kiek jame yra simbolių (įskaitant tarpų ir specialių simbolių).
            symbols++;
            //1. Žodžių skaičius paskaičiuoja, kiek jame yra žodžių.
            if (words == 0 && demoText.charAt(index) != ' '){
                words++;
            }
            if (demoText.charAt(index) == ' '&& demoText.charAt(index+1)!=' '){
                words++;
            }
            // 3. Dažniausiai pasikartojantis simbolis: Randa dažniausiai pasikartojantį simbolį tekste.
            if (demoText.charAt(index) != mostCommonSymbol) {
                int mostCommonTemp = 0;
                for (int secondIndex = 0; secondIndex < demoText.length(); secondIndex++) {
                    // Tikrina kiek dažnai dabartinis simbolis pasikartoja tekste
                    if (demoText.charAt(index) == demoText.charAt(secondIndex)) {
                        mostCommonTemp++;
                    }
                    // Jei pasikartoja dažniau nei dažniausiai pasitaikantis, jis priskiriamas dažniausiai pasitaikančiam
                    if (mostCommonTemp > mostCommonOccurrence) {
                        mostCommonOccurrence = mostCommonTemp;
                        mostCommonSymbol = demoText.charAt(index);
                    }
                }
            }
        }
        System.out.printf("Tekste yra %d simbolių \n",symbols);
        System.out.printf("Tekste yra %d žodžių \n",words);
        System.out.printf("Tekste dažniausiai pasitaiko \"%s\" simbolis \n", mostCommonSymbol);
        System.out.printf("Tekste šis simbolis pasitaiko %d kartų \n",mostCommonOccurrence);
        demoText.reverse();
        System.out.printf("Visas tekstas atvirkščiai: %s",demoText);


    }
}
