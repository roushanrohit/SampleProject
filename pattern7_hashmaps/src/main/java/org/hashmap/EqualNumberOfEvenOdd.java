package org.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualNumberOfEvenOdd {

    public static void main(String[] args) {
                           // 0   1   2  3   4  5
        int[] arr = new int[]{4, -1, -1, 1, -2, 3};
                           // 1   0  -1 -2  -1 -2
        System.out.println("total number of subsets with equal number of even and odds : "
                + subsetsWithEqualNumberOfEvenAndOdds(arr));

    }

    private static int subsetsWithEqualNumberOfEvenAndOdds(int[] arr) {

        /*
           Idea is to represent even with 1 and odd with -1
           then prepare a prefix sum hashmap with value = list of indices and key = sum till those indices
         */
        Map<Integer, List<Integer>> hmap = new HashMap<>();
        int sum = 0;
        int count = 0;

        for(int i = 0; i < arr.length; i++){

            sum += (arr[i] % 2 == 0 ? 1 : -1);
            if(sum == 0) {
                count++;
            } else if (hmap.containsKey(sum)){
                count += hmap.get(sum).size();
            }

            List<Integer> indices = hmap.get(sum);
            if(indices == null) indices = new ArrayList<>();
            indices.add(i);
            hmap.put(sum, indices);
        }

        System.out.println(hmap);
        return count;
    }
}
