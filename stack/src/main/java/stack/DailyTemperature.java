package stack;

import java.util.Arrays;
import java.util.Stack;

/*
    Given an array temperatures, where temperatures[i] is the temperature on day i,
    return an array where answer[i] tells you how many days you have to wait until a warmer temperature.
    If there is no warmer day, return 0.
 */
public class DailyTemperature {

    public static void main(String[] args) {

        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] ans = dailyTemperatures(temperatures, temperatures.length);
        for(int num : ans){
            System.out.print(num + " ");
        }
    }

    private static int[] dailyTemperatures(int[] temperatures, int n) {

        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        for(int i = n - 1; i >= 0; i--){
            while(!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                ans[i] = stack.peek() - i;
            }
            stack.push(i);
        }
        return ans;
    }
}
