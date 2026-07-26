package misc;

/*
    Given an integer array nums, return an array answer such that answer[i] is equal to the
    product of all the elements of nums except nums[i].
    The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
    You must write an algorithm that runs in O(n) time and without using the division operation.
 */
public class ProductExceptSelf {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        int[] productExceptSelf = productExceptSelf(nums);
        for(int i = 0; i < productExceptSelf.length; i++){
            System.out.print(productExceptSelf[i] + " ");
        }
    }

    public static int[] productExceptSelf(int[] nums) {
        // we will initialize two arrays for the left sum and right sum
        int[] left = new int[nums.length];
        left[0] = 1;
        for(int i = 1; i < nums.length; i++){
            left[i] = left[i - 1] * nums[i - 1];
        }
        int[] right = new int[nums.length];
        right[nums.length - 1] = 1;
        for(int i = nums.length - 2; i >= 0; i--){
            right[i] = right[i + 1] * nums[i + 1];
        }

        int[] ans = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            ans[i] = left[i] * right[i];
        }
        return ans;
    }
}
