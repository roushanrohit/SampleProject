package org.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountSubsetsWithSumK {

    public static void main(String[] args) {

        int[] arr = new int[]{2, 3, 5, 6, 8, 10};
        int k = 10;
        List<int[]> indicesOfSubsetSumK = subsetsWihSumK(arr, k);
        for(int[] a : indicesOfSubsetSumK){
            System.out.println("startIndex: " + a[0] + " , endIndex: " + a[1]);
        }
    }

    private static List<int[]> subsetsWihSumK(int[] arr, int k){

        List<int[]> ans = new ArrayList<>();
        // key -- prefix sum, value -- indices
        Map<Integer, List<Integer>> prefixSumMap = new HashMap<>();
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
            if(sum == k){
                ans.add(new int[]{0, i});
            } else if(prefixSumMap.containsKey(sum - k)){
                for(int index : prefixSumMap.get(sum - k)){
                    ans.add(new int[]{index + 1, i});
                }
            }
            prefixSumMap.computeIfAbsent(sum, m -> new ArrayList<>()).add(i);
        }
        return ans;
    }
}
