package binarysearch;

public class MaxInRotatedSortedArray {

    public static void main(String[] args) {

        int[] nums = {4,5,6,7,0,1,2};
        System.out.println("Maximum element in the array is: " + maxInRotatedArray(nums, 0, nums.length-1));
    }

    private static int maxInRotatedArray(int[] arr, int left, int right) {

        int max = Integer.MIN_VALUE;
        while(left <= right){

            int mid = (left + right)/2;
            if (arr[left] == arr[mid] && arr[mid] == arr[right]) {
                // ambiguous -- can't tell where the break is, shrink linearly
                max = Math.max(max, arr[left]);
                left++;
                right--;
            } else if(arr[left] <= arr[mid]){
                // left part is sorted
                max = Math.max(max, arr[mid]);
                left = mid + 1;
            } else {
                // right part is sorted
                max = Math.max(max, arr[right]);
                right = mid - 1;
            }
        }

        return max;
    }
}
