package org.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
    such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 */
public class TripletSumZero {

    public static void main(String[] args) {

        int[] arr = new int[]{-1,0,1,2,-1,-4};
        List<int[]> allTriplets = tripletsWithSumZero2(arr);
        for(int[] triplet : allTriplets){
            System.out.println(triplet[0] + " " + triplet[1] + " " + triplet[2]);
        }
    }

    private static List<int[]> tripletsWithSumZero2(int[] arr) {

        List<int[]> triplets = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){
            int currentElement = arr[i];
            List<int[]> pairs = pairsWithSumK2(arr, i + 1, currentElement * -1);
            if(!pairs.isEmpty()){
                for(int[] pair : pairs){
                    triplets.add(new int[]{pair[0], pair[1], currentElement});
                }
            }
        }

        return triplets;
    }

    private static List<int[]> pairsWithSumK2(int[] arr, int si, int k) {

        Map<Integer, Integer> hmap = new HashMap<>();
        List<int[]> allPairs = new ArrayList<>();

        for(int i = si; i < arr.length; i++){
            // check for compliment
            if(hmap.containsKey(k - arr[i])){
                int count = hmap.get(k - arr[i]);
                for(int j = 0; j < count; j++){
                    allPairs.add(new int[]{arr[i], k - arr[i]});
                }
            }
            // put in hashmap
            hmap.put(arr[i], hmap.getOrDefault(arr[i], 0) + 1);
        }

        return allPairs;
    }
}
