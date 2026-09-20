package org.slidingwindows.fixed;

/*
    You are given an array of 0s and 1s and an integer k, you need to find the number of windows
    of size k which do not have 2 consecutive 0s or 2 consecutive 1s
 */
public class BadPairs {

    public static void main(String[] args) {

        int[] arr = {0,1,0,1};
        int k = 3;
        System.out.println(validWindows(arr, k));
    }

    private static int validWindows(int[] arr, int k) {

        int validWindowsCount = 0;
        int badPairs = 0;
        for(int i = 0; i < k - 1; i++){
            if(arr[i] == arr[i + 1]){
                badPairs++;
            }
        }
        if(badPairs == 0) {
            validWindowsCount++;
        }

        for(int j = k; j < arr.length; j++){
            // incoming number
            if(arr[j - 1] == arr[j]){
                badPairs++;
            }
            // outgoing number
            if(arr[j - k] == arr[j - k + 1]){
                badPairs--;
            }
            if(badPairs == 0){
                validWindowsCount++;
            }
        }

        return validWindowsCount;
    }
}
