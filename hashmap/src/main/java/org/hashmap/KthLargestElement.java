package org.hashmap;

import java.util.PriorityQueue;

public class KthLargestElement {

    public static void main(String[] args) {

        int[] arr = {3,2,1,5,6,4};
        int k = 2;
        System.out.println(k + "th largest element : " + kthlargestEelement(arr, k));
    }

    private static int kthlargestEelement(int[] arr, int k) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int num : arr){
            minHeap.offer(num);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }
}
