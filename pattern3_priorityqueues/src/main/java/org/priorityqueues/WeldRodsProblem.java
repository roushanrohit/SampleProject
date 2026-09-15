package org.heap;

import java.util.PriorityQueue;

/*
    Given an array of rod lengths, weld all rods into a single rod with the minimum total cost
    The cost to weld two rods is the sum of their lengths

    Approach: To minimize the total cost, we want smaller rods to be reused more often and
              larger rods to be reused fewer times, since rod length contributes to the cost

              You might think ok we'll sort the array first and take two rods at a time
              [1,2,3,100]
              1+2 = 3 cost = 3
              3+100 = 103 cost = 106
              3+103 = 106 cost = 212

              But the minimum cost is:
              [1,2,3,100]
              1+2 = 3 cost = 3
              3+3 = 6 cost = 6
              6+100 = 106 cost = 115

              After every merge:
              1. Remove two elements
              2. Insert the merged rod at the correct position
              O(n) insertion * (n-1) merges = O(N^2)
              After every operation, I create a new value that must participate in future minimum selections

              Better Approach:
              We'll use min heap for this -- Priority Queue
              [1,2,3,100]
              1. Poll two minimum -- 1, 2 cost = 3, add new rod to the heap
              2. [3,3,100] Poll two minimum -- 3, 3 cost = 6, add new rod to the heap
              3. [6,100] Poll two minimum -- 6, 100 cost = 106, heap is empty

              Repeatedly choose largest/smallest element from a changing set -- Priority Queue

 */
public class WeldRodsProblem {

    public static void main(String[] args) {

        int[] arr = {1,2,3,100};
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : arr) {
            pq.offer(num);
        }
        int cost = 0;
        while(pq.size() > 1){
            int firstRod = pq.poll();
            int secondRod = pq.poll();
            int merged = firstRod + secondRod;
            cost += merged;

            // merged rod -- will participate in future minimum selections
            pq.offer(merged);
        }
        System.out.println(cost);
    }
}
