package misc;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {

        int[] arr = new int[]{5,1,2,3};
        System.out.println("Longest increasing subsequence in the array is : " + longestIncreasingSubsequenceDp(arr));
        System.out.println("Longest increasing subsequence in the array is : " + longestIncreasingSubsequence(arr));
    }

    // DP Solution
    private static List<Integer> longestIncreasingSubsequenceDp(int[] arr) {

        int[] subsequence = new int[arr.length];
        int[] prev = new int[arr.length];
        for(int i = 0; i < arr.length; i++){

            int maxLengthSubsequence = 0;
            int prevIndex = -1;
            for(int j = 0; j < i; j++){
                if(arr[j] < arr[i] && subsequence[j] > maxLengthSubsequence){
                    maxLengthSubsequence = subsequence[j];
                    prevIndex = j;
                }
            }
            subsequence[i] = maxLengthSubsequence + 1;
            prev[i] = prevIndex;
        }

        int maxIndex = -1;
        int maxLength = 0;
        for(int i = 0; i < subsequence.length; i++){
            if(subsequence[i] > maxLength){
                maxLength = subsequence[i];
                maxIndex = i;
            }
        }

        List<Integer> ans = new ArrayList<>();
        while(maxIndex > -1){
            ans.add(arr[maxIndex]);
            maxIndex = prev[maxIndex];
        }
        return ans;
    }

    // Normal Recursion Solution
    private static List<Integer> longestIncreasingSubsequence(int[] arr) {

        List<Integer> overAllBest = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            List<Integer> endingAtI = lisEndingAtI(arr, i);
            if(endingAtI.size() > overAllBest.size()){
                overAllBest = endingAtI;
            }
        }
        return overAllBest;
    }

    // LIS that ends EXACTLY at index i (arr[i] is always the last element)
    private static List<Integer> lisEndingAtI(int[] arr, int i) {

        // base case
        if(i == 0) {
            List<Integer> ans = new ArrayList<>();
            ans.add(arr[0]);
            return ans;
        }

        List<Integer> ans = new ArrayList<>();
        for(int j = 0; j < i; j++){
            if(arr[j] < arr[i]){
                List<Integer> smallAns = lisEndingAtI(arr, j);
                if(smallAns.size() > ans.size()){
                    ans = smallAns;
                }
            }
        }

        ans.add(arr[i]);
        return ans;
    }
}
