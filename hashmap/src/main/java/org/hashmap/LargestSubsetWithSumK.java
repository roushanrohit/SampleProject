package org.hashmap;

import java.util.HashMap;
import java.util.Map;

public class LargestSubsetWithSumK {

    public static void main(String[] args) {

        int[] arr = new int[]{4,-1,-1,1,-2,3};
        int k = 5;
        System.out.println("length of largest subset with k sum: " + largestSubsetWithSumK(arr, k));

    }

    private static int largestSubsetWithSumK(int[] arr, int k) {

        // value = index, key = sum till that index
        Map<Integer, Integer> hmap = new HashMap<>();
        int sum = 0;
        int largest = 0;

        for(int i = 0; i < arr.length; i++){

            sum += arr[i];
            if(sum == k) {
                largest = i + 1;
            } else if (hmap.containsKey(sum - k)){
                largest = Math.max(largest, i - hmap.get(sum - k));
            } else {

                // store first occurrence only
                hmap.put(sum, i);
            }
        }
        return largest;
    }
}
