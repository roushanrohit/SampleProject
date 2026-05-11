package org.hashmap;

import java.util.HashMap;
import java.util.Map;

public class PairsWithSumK {

    public static void main(String[] args) {

        int[] arr = new int[]{4,1,1,1,2,3};
        int k = 2;
        System.out.println("Count of pairs with sum " + k + " : " + pairsWithSumK(arr, k));
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
            if(hmap.containsKey(num)){
                hmap.put(num, hmap.get(num) + 1);
            } else {
                hmap.put(num, 1);
            }
        }

        return count;
    }


}
