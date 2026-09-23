package stack;

import java.util.Arrays;
import java.util.Stack;

/*
    Given: nums = [1, 3, 4, 2]
    For every element, find the next greater element, considering that after the last element
    we wrap around to the beginning.
 */
public class NextGreaterElementRotated {

    public static void main(String[] args) {

        int[] nums = {1, 3, 4, 2};
        int[] ans = nextGreaterElement(nums);
        for(int num : ans){
            System.out.print(num + " ");
        }
        System.out.println();
        int[] ans2 = prevGreaterElement(nums);
        for(int num : ans2){
            System.out.print(num + " ");
        }
    }

    private static int[] nextGreaterElement(int[] nums) {

        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();

        for(int i = 2 * n - 1; i >= 0; i--){
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }
            if (i < n && !stack.isEmpty()) {
                ans[i] = stack.peek();
            }
            stack.push(nums[i % n]);
        }

        return ans;
    }

    private static int[] prevGreaterElement(int[] nums) {

        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < 2 * n; i++){
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }
            if (i >= n && !stack.isEmpty()) {
                ans[i % n] = stack.peek();
            }
            stack.push(nums[i % n]);
        }

        return ans;
    }
}
