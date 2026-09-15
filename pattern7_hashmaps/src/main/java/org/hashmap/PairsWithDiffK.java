package org.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairsWithDiffK {

    public static void main(String[] args) {

        int[] arr = new int[]{4,1,1,1,2,3};
        int k = 2;
        System.out.println("Count of pairs with difference " + k + " : " + pairsWithDiffK(arr, k));
        List<int[]> allPairs = pairsWithDiffK2(arr, k);
        for(int[] pair : allPairs){
            System.out.println(pair[0] + " " + pair[1]);
        }
    }

    private static int pairsWithDiffK(int[] arr, int k) {

        Map<Integer, Integer> hmap = new HashMap<>();
        int count = 0;

        for (int num : arr) {
            // check for compliment
            if (hmap.containsKey(num - k)) {
                count += hmap.get(num - k);
            }
            if (k != 0 && hmap.containsKey(num + k)) {
                count += hmap.get(num + k);
            }

            // put in hashmap
            hmap.put(num, hmap.getOrDefault(num, 0) + 1);
        }
        return count;
    }

    private static List<int[]> pairsWithDiffK2(int[] arr, int k) {

        Map<Integer, Integer> hmap = new HashMap<>();
        List<int[]> allPairs = new ArrayList<>();

        for (int num : arr) {
            // check for compliment
            if (hmap.containsKey(num - k)) {
                int count = hmap.get(num - k);
                for(int j = 0; j < count; j++){
                    allPairs.add(new int[]{num, num - k});
                }
            }
            if (k != 0 && hmap.containsKey(num + k)) {
                int count = hmap.get(num + k);
                for(int j = 0; j < count; j++){
                    allPairs.add(new int[]{num, num + k});
                }
            }

            // put in hashmap
            hmap.put(num, hmap.getOrDefault(num, 0) + 1);
        }
        return allPairs;
    }
}
