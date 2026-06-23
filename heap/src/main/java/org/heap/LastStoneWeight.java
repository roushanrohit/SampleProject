package org.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/*
    Given an array of weights of stones, you need to smash two stones at once,
    if both stones are equal in weight, both disappear
    else they create a new stone of weight equal to the difference of the weights of the two stones

    eg: [2,7,4,1,8,1]
    We'll use max heap for this -- Priority Queue
      [2,7,4,1,8,1]
      1. Poll two maximums -- 8, 7 different weights -- a new stone of weight (8-7 = 1) is created, add new stone to the heap
      2. [2,4,1,1,1] Poll two maximums -- 2, 4 different weights -- a new stone of weight (4-2 = 2) is created, add new stone to the heap
      3. [2,1,1,1] Poll two maximums -- 2, 1 different weights -- a new stone of weight (2-1 = 1) is created, add new stone to the heap
      4. [1,1,1] Poll two maximums -- 1, 1 same weight -- both will disappear
      5. [1] -- last stone weight
 */
public class LastStoneWeight {

    public static void main(String[] args) {

        int[] arr = {2,7, 4, 1, 8, 1};
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // max heap
        for (int num : arr) {
            pq.offer(num);
        }
        while(pq.size() > 1){
            int firstStone = pq.poll();
            int secondStone = pq.poll();
            if(firstStone - secondStone > 0){
                // add newly created stone to the heap
                pq.offer(firstStone - secondStone);
            }
        }

        int lastStoneWeight = !pq.isEmpty() ? pq.poll() : -1;
        System.out.println(lastStoneWeight);
    }
}
