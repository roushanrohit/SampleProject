package org.hashmap;

import java.util.*;
import java.util.HashMap;

public class TopKFrequentElements {

    public static void main(String[] args) {

        int[] arr = {1,1,1,2,2,3};
        int k = 2;
        System.out.println("Top " + k + " frequent elemnts : " + topKFrequentElements(arr, k));
        System.out.println("Top " + k + " least frequent elemnts : " + topKLeastFrequentElements(arr, k));
    }

    private static List<Integer> topKFrequentElements(int[] arr, int k) {

        // build the frequency hashmap
        Map<Integer, Integer> hmap = new HashMap<>();
        for(int num : arr){
            if(hmap.containsKey(num)){
                hmap.put(num, hmap.get(num) + 1);
            } else {
                hmap.put(num, 1);
            }
        }

        // priority queue is a min heap by default
        // but here it is not comparing the integers rather it is comparing their frequencies in the map
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(hmap::get));

        for(int num : hmap.keySet()){

            minHeap.offer(num);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }

        return new ArrayList<>(minHeap);
    }

    private static List<Integer> topKLeastFrequentElements(int[] arr, int k) {

        // build the frequency hashmap
        Map<Integer, Integer> hmap = new HashMap<>();
        for(int num : arr){
            if(hmap.containsKey(num)){
                hmap.put(num, hmap.get(num) + 1);
            } else {
                hmap.put(num, 1);
            }
        }

        // priority queue is a min heap by default
        // but here it is not comparing the integers rather it is comparing their frequencies in the map
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.comparingInt(hmap::get).reversed());

        for(int num : hmap.keySet()){

            maxHeap.offer(num);
            if(maxHeap.size() > k){
                maxHeap.poll();
            }
        }

        return new ArrayList<>(maxHeap);
    }
}
