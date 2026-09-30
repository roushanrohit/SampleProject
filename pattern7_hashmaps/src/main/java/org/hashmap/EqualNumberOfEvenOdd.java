package org.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualNumberOfEvenOdd {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 1, 1, 1, 2, 3};
        List<int[]> subSetsWithEqualEvenAndOdd = subsetsWithEqualNumberOfEvenAndOdds(arr);
        for(int[] a : subSetsWithEqualEvenAndOdd){
            System.out.println("startIndex: " + a[0] + " , endIndex: " + a[1]);
        }
    }

    private static List<int[]> subsetsWithEqualNumberOfEvenAndOdds(int[] arr) {

        List<int[]> ans = new ArrayList<>();
        /*
           Idea is to represent even with 1 and odd with -1, equal number of odds and even means k = 0
           then prepare a prefix sum hashmap with value = list of indices and key = sum till those indices
         */
        Map<Integer, List<Integer>> prefixSumMap = new HashMap<>();
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum += (arr[i] % 2 == 0 ? 1 : -1);
            if(sum == 0) {
                ans.add(new int[]{0, i});
            } else if (prefixSumMap.containsKey(sum)){
                for(int index : prefixSumMap.get(sum)){
                    ans.add(new int[]{index + 1, i});
                }
            }
            prefixSumMap.computeIfAbsent(sum, m -> new ArrayList<>()).add(i);
        }
        return ans;
    }
}
