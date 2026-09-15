package org.heap;

import java.util.PriorityQueue;

public class KthLargestElement {

    public static void main(String[] args) {

        int[] arr = {2,7, 4, 1, 8, 1};
        int k =3;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num : arr){
            pq.offer(num);
            if(pq.size() > k){
                pq.poll();
            }
        }

        System.out.println(pq.peek());
    }
}
