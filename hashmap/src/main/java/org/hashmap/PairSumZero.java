package org.hashmap;

import java.util.HashMap;
import java.util.Map;

public class PairSumZero {

    public static void main(String[] args) {

        int[] arr = new int[]{4,-1,-1,1,-2,3};
        System.out.println("Count of pairs with zero sum: " + pairsWithSumZero(arr));
    }

    private static int pairsWithSumZero(int[] arr) {

        Map<Integer, Integer> hmap = new HashMap<>();
        int count = 0;

        for (int num : arr) {

            if (hmap.containsKey(num * -1)) {
                count += hmap.get(num * -1);
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
