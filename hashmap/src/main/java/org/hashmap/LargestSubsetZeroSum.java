package org.hashmap;

import java.util.HashMap;
import java.util.Map;

public class LargestSubsetZeroSum {

    public static void main(String[] args) {

        int[] arr = new int[]{4,-1,-1,1,-2,3};
        System.out.println("length of largest subset with zero sum: " + largestSubsetWithZeroSum(arr));

    }

    private static int largestSubsetWithZeroSum(int[] arr) {

        // value = index, key = sum till that index
        Map<Integer, Integer> hmap = new HashMap<>();
        int sum = 0;
        int largest = 0;

        for(int i = 0; i < arr.length; i++){

            sum += arr[i];
            if(sum == 0) {
                largest = i + 1;
            } else if (hmap.containsKey(sum)){
                largest = Math.max(largest, i - hmap.get(sum));
            } else {

                // store first occurrence only
                hmap.put(sum, i);
            }
        }
        return largest;
    }
}
