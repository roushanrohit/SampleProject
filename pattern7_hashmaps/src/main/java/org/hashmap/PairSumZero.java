package org.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairSumZero {

    public static void main(String[] args) {

        int[] arr = new int[]{4,-1,-1,1,-2,3};
        System.out.println("Count of pairs with zero sum: " + pairsWithSumZero(arr));
        List<int[]> allPairs = pairsWithSumZero2(arr);
        for(int[] pair : allPairs){
            System.out.println(pair[0] + " " + pair[1]);
        }
    }

    private static int pairsWithSumZero(int[] arr) {

        Map<Integer, Integer> hmap = new HashMap<>();
        int count = 0;

        for (int num : arr) {
            if (hmap.containsKey(num * -1)) {
                count += hmap.get(num * -1);
            }
            // put in hashmap
            hmap.put(num, hmap.getOrDefault(num, 0) + 1);
        }
        return count;
    }

    private static List<int[]> pairsWithSumZero2(int[] arr) {

        Map<Integer, Integer> hmap = new HashMap<>();
        List<int[]> allPairs = new ArrayList<>();

        for (int num : arr) {
            if (hmap.containsKey(num * -1)) {
                int count = hmap.get(num * -1);
                for(int j = 0 ; j < count ; j++){
                    allPairs.add(new int[]{num, num * -1});
                }
            }
            // put in hashmap
            hmap.put(num, hmap.getOrDefault(num, 0) + 1);
        }
        return allPairs;
    }
}
