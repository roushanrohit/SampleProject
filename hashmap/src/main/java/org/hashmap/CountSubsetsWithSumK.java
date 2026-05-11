package org.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountSubsetsWithSumK {

    public static void main(String[] args) {

        int[] arr = new int[]{4,-1,-1,1,-2,3};
        int k = 5;
        System.out.println("number of subsets with k sum: " + countSubsetWithSumK(arr, k));

    }

    private static int countSubsetWithSumK(int[] arr, int k) {

        // value = indices, key = sum till that index
        Map<Integer, List<Integer>> hmap = new HashMap<>();
        int sum = 0;
        int count = 0;

        for(int i = 0; i < arr.length; i++){

            sum += arr[i];
            if(sum == k) {
                count++;
            } else if (hmap.containsKey(sum - k)){
                count += hmap.get(sum - k).size();
            }

            List<Integer> indices = hmap.get(sum);
            if(indices == null) indices = new ArrayList<>();
            indices.add(i);
            hmap.put(sum, indices);
        }
        return count;
    }
}
