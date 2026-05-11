package org.dp;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {

        int[] arr = new int[]{3,1,3,4,9,7,8};
        System.out.println("Longest increasing subsequence in the array is : " + longestIncreasingSubsequence(arr));
    }

    private static List<Integer> longestIncreasingSubsequence(int[] arr) {
        return longestIncreasingSubsequence(arr, arr.length - 1);
    }

    private static List<Integer> longestIncreasingSubsequence(int[] arr, int i) {

        if(i == 0) {
            // cannot use List.of since it gives an immutable list
            List<Integer> ans = new ArrayList<>();
            ans.add(arr[0]);
            return ans;
        }

        List<Integer> ans = new ArrayList<>();
        for(int j = 0; j < i; j++){
            List<Integer> smallAns = longestIncreasingSubsequence(arr, j);
            int lastElement = smallAns.get(smallAns.size() - 1);
            if(lastElement < arr[i]) {
                smallAns.add(arr[i]);
            }
            if(smallAns.size() > ans.size()){
                ans = smallAns;
            }
        }

        return ans;
    }
}
