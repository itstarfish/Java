import java.util.ArrayList;
import java.util.Arrays;

public class uzduotis {
    public static void main(String[] args) {
        int[] numbers = {7,1,2,9,9,7,2,1};
        System.out.println("has22: " + has22(numbers));
        System.out.println("maxMirror: "+ maxMirror(numbers));
    }
    public static int maxMirror(int[] numbers){
        int maxLength = 0;
        //Array ---->
        for (int i = 0; i < numbers.length; i++) {
            //Array <----
            for (int j = numbers.length - 1; j >= 0; j--) {
                int tempLength = 0;
                //Array --->  <  length
                while ((i + tempLength < numbers.length)
                        //&& Array <----  >=  0
                        && (j - tempLength >= 0)
                        //&& Array ---->  ==  <----
                        && (numbers[i + tempLength]) == (numbers[j - tempLength])) {
                    System.out.println("----> "+ numbers[i + tempLength] + " <---- " + numbers[j - tempLength] + " maxLength: " + maxLength + " tempLength: " + (tempLength+1));
                    //Increase tempLength
                    tempLength++;
                }
                if (tempLength > maxLength) maxLength = tempLength;
            }
        }
        return maxLength;
    }
    public static boolean has22(int[] numbers){
        //Array ---->
        for (int i = 0; i < numbers.length; i++){
            if (i < numbers.length-1){
                if (numbers[i] == numbers[i+1]){
                    return true;
                }
            }
        }
        return false;
    }
}
