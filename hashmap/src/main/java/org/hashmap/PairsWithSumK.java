package org.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairsWithSumK {

    public static void main(String[] args) {

        int[] arr = new int[]{4,1,1,1,2,3};
        int k = 5;
        System.out.println("Count of pairs with sum " + k + " : " + pairsWithSumK(arr, k));
        List<int[]> allPairs = pairsWithSumK2(arr, k);
        for(int[] pair : allPairs){
            System.out.println(pair[0] + " " + pair[1]);
        }
    }

    private static int pairsWithSumK(int[] arr, int k) {

        Map<Integer, Integer> hmap = new HashMap<>();
        int count = 0;

        for(int num : arr){
            // check for compliment
            if(hmap.containsKey(k - num)){
                count += hmap.get(k - num);
            }
            // put in hashmap
            hmap.put(num, hmap.getOrDefault(num, 0) + 1);
        }

        return count;
    }

    private static List<int[]> pairsWithSumK2(int[] arr, int k) {

        Map<Integer, Integer> hmap = new HashMap<>();
        List<int[]> allPairs = new ArrayList<>();

        for(int num : arr){
            // check for compliment
            if(hmap.containsKey(k - num)){
                int count = hmap.get(k - num);
                for(int j = 0; j < count; j++){
                    allPairs.add(new int[]{num, k - num});
                }
            }
            // put in hashmap
            hmap.put(num, hmap.getOrDefault(num, 0) + 1);
        }

        return allPairs;
    }


}
