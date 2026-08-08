package stack;

import java.util.Arrays;
import java.util.Stack;

/*
    Given: nums = [1, 2, 1]
    For every element, find the next greater element, considering that after the last element
    we wrap around to the beginning.
 */
public class NextGreaterElement2 {

    public static void main(String[] args) {

        int[] nums = {1,2,1};
        int[] ans = nextGreaterElement(nums, nums.length);
        for(int num : ans){
            System.out.print(num + " ");
        }
    }

    /*
        Given arr is: [1,2,1]
        The trick is we iterate the array two times:
        i        i % n      value
        5          2          1
        4          1          2
        3          0          1
        2          2          1
        1          1          2
        0          0          1
     */
    private static int[] nextGreaterElement(int[] nums, int n) {

        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        for(int i = 2 * n - 1; i >= 0; i--){

            int current = nums[i % n];
            while (!stack.isEmpty() && stack.peek() <= current) {
                stack.pop();
            }
            if (i < n && !stack.isEmpty()) {
                ans[i] = stack.peek();
            }
            stack.push(current);
        }
        return ans;
    }
}
