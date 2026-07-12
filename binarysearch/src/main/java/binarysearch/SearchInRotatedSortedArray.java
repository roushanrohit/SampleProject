package binarysearch;

/*
    Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length)
    such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
    For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].
    Given the array nums after the possible rotation and an integer target, return the index of target
    if it is in nums, or -1 if it is not in nums.

    Note: We are considering only distinct elements in this problem.
 */
public class SearchInRotatedSortedArray {

    public static void main(String[] args) {

        int[] nums = {4,5,6,7,0,1,2};
        int target = 8;
        System.out.println("Target present in the array at index: " + searchRotatedArray(nums, target, 0, nums.length-1));
    }

    private static int searchRotatedArray(int[] arr, int target, int left, int right) {

        while(left <= right){

            int mid = (left + right)/2;
            if(arr[mid] == target) return mid;

            if(arr[left] <= arr[mid]){
                // left part is sorted
                if(arr[left] <= target && target < arr[mid]){
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // right part is sorted
                if(arr[mid] < target && target <= arr[right]){
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
