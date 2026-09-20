package org.slidingwindows.fixed;

import java.util.*;

public class MaximumOfAllSubArraysOfSizeK {

    public static void main(String[] args) {

        int[] arr = new int[]{1,12,-5,-6, 50, 3};
        int k = 4;
        List<Integer> maximumOfAllSubArraysOfSizeK = maximumSubArraySizeK(arr, k);
        System.out.println(maximumOfAllSubArraysOfSizeK);
    }

    private static List<Integer> maximumSubArraySizeK(int[] arr, int k) {

        List<Integer> ans = new ArrayList<>();
        TreeSet<int[]> tSet = new TreeSet<>(Comparator.comparingInt((int[] a) -> a[0]).reversed()
                .thenComparing(a -> a[1]));
        for(int i = 0; i < k; i++){
            tSet.add(new int[]{arr[i], i});
        }
        ans.add(tSet.first()[0]); // maximum of first window
        for(int j = k; j < arr.length; j++){
            tSet.remove(new int[]{arr[j - k], j - k});
            tSet.add(new int[]{arr[j], j});
            ans.add(tSet.first()[0]);
        }

        return ans;
    }
}
