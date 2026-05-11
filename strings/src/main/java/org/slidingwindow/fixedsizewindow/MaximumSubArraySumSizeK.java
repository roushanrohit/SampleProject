package org.slidingwindow.fixedsizewindow;

public class MaximumSubArraySumSizeK {

    public static void main(String[] args) {

        int[] arr = new int[]{1,12,-5,-6, 50, 3};
        int k = 4;
        System.out.println(maximumSubArraySumSizeK(arr, k));
    }

    private static int maximumSubArraySumSizeK(int[] arr, int k) {

        int n = arr.length;
        if(n < k) return -1; // edge case

        // compute sum of first window
        int windowSum = 0;
        for(int i = 0; i < k; i++){
            windowSum += arr[i];
        }

        int maxSum = windowSum;

        // slide the window
        for(int end = k; end < n; end++){
            windowSum += arr[end]; // add next element
            windowSum -= arr[end - k]; // remove element going out

            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }
}
