package binarysearch;

// With duplicate elements
public class SearchInRotatedSortedArray2 {

    public static void main(String[] args) {

        int[] nums = {3, 1, 2, 3, 3, 3, 3};
        int target = 4;
        System.out.println("Target present in the array : " + searchRotatedArray(nums, target, 0, nums.length-1));
    }

    private static boolean searchRotatedArray(int[] arr, int target, int left, int right) {

        while(left <= right){

            int mid = (left + right)/2;
            if(arr[mid] == target) return true;

            if(arr[left] == arr[mid] && arr[mid] == arr[right]){
                // can't tell which side is sorted -- shrink both ends
                left++;
                right--;
            } else if(arr[left] <= arr[mid]){
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

        return false;
    }
}
