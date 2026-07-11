package stack;

import java.util.Map;
import java.util.Stack;

public class ValidParenthesis {

    public static void main(String[] args) {

        System.out.println(isValid("([)]"));
    }

    public static boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        Map<Character, Character> pairs = Map.of(')', '(', '}', '{', ']', '[');

        for(int i = 0; i < s.length(); i++){

            char ch = s.charAt(i);
            if(pairs.containsKey(ch)){

                // its a closing bracket
                if(!stack.isEmpty() && stack.peek() == pairs.get(ch)){
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            } else {
                // its a opening bracket
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }
}
