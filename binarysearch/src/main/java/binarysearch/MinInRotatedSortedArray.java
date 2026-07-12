package binarysearch;

public class MinInRotatedSortedArray {

    public static void main(String[] args) {

        int[] nums = {4,5,6,7,0,1,2};
        System.out.println("Minimum element in the array is: " + minInRotatedArray(nums, 0, nums.length-1));
    }

    private static int minInRotatedArray(int[] arr, int left, int right) {

        int min = Integer.MAX_VALUE;
        while(left <= right){

            int mid = (left + right)/2;
            if (arr[left] == arr[mid] && arr[mid] == arr[right]) {
                // ambiguous -- can't tell where the break is, shrink linearly
                min = Math.min(min, arr[left]);
                left++;
                right--;
            } else if(arr[left] <= arr[mid]){
                // left part is sorted
                min = Math.min(min, arr[left]);
                left = mid + 1;
            } else {
                // right part is sorted
                min = Math.min(min, arr[mid]);
                right = mid - 1;
            }
        }

        return min;
    }
}
