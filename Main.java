import java.util.LinkedList;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        palidromeChecker("feteetef");
    }

    public static boolean palidromeChecker(String toCheck){
        LinkedList<Character> stack = new LinkedList<>();
        toCheck = toCheck.toLowerCase();
        for(int i = 0; i< toCheck.length();i++){
            stack.push(toCheck.charAt(i));
        }
        for(int i = 0; i< toCheck.length();i++){
            if (stack.size()> 0 && stack.peek() == toCheck.charAt(i)){
                System.out.println("toCheck: " + toCheck.charAt(i) + " Stack: " + stack.peek());
                stack.pop();
                stack.remove(0);

            } else return false;
        }
        return true;
    }
}