package org.hashmap;

import java.util.HashMap;
import java.util.Map;

public class PairsWithDiffK {

    public static void main(String[] args) {

        int[] arr = new int[]{4,1,1,1,2,3};
        int k = 2;
        System.out.println("Count of pairs with difference " + k + " : " + pairsWithDiffK(arr, k));
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
            if (hmap.containsKey(num)) {
                hmap.put(num, hmap.get(num) + 1);
            } else {
                hmap.put(num, 1);
            }
        }
        return count;
    }
}
