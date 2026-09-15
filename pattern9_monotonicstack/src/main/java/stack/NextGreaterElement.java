package stack;

import java.util.Arrays;
import java.util.Stack;

/*
    You are given an array:
    nums = [1, 3, 4, 2]
    For every element, find the first greater element to its right. If there isn't one, take -1.
 */
public class NextGreaterElement {

    public static void main(String[] args) {

        int[] nums = {1,3,4,2};
        int[] ans = nextGreaterElement(nums);
        for(int num : ans){
            System.out.print(num + " ");
        }
    }

    private static int[] nextGreaterElement(int[] nums) {

        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();

        for(int i = nums.length - 1; i >= 0; i--){
            int current = nums[i];
            while(!stack.isEmpty() && stack.peek() <= current){
                stack.pop();
            }
            if (!stack.isEmpty()) {
                ans[i] = stack.peek();
            }
            stack.push(current);
        }

        return ans;
    }
}
