package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
    You are given two arrays:
    nums1 = [4, 1, 2]
    nums2 = [1, 3, 4, 2]
    For every element in nums1, find the first greater element to its right in nums2. If there isn't one, return -1.
 */
public class NextGreaterElement {

    public static void main(String[] args) {

        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        int[] ans = nextGreaterElement(nums1, nums2);
        for(int num : ans){
            System.out.print(num + " ");
        }
    }

    private static int[] nextGreaterElement(int[] nums1, int[] nums2) {

        Map<Integer, Integer> hmap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for(int i = nums2.length - 1; i >= 0; i--){
            while(!stack.isEmpty() && stack.peek() <= nums2[i]){
                stack.pop();
            }
            if (stack.isEmpty()) {
                hmap.put(nums2[i], -1);
            } else {
                hmap.put(nums2[i], stack.peek());
            }
            stack.push(nums2[i]);
        }
        int[] ans = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++){
            ans[i] = hmap.get(nums1[i]);
        }
        return ans;
    }
}
