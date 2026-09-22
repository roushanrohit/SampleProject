package org.priorityqueues;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFromDataStream {

    public static void main(String[] args) {

        PriorityQueue<Integer> maxHeapLo = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minHeapRo = new PriorityQueue<>();

        int[] arr = {41,35,62,5,97,100};
        for(int num : arr){
            addNum(maxHeapLo, minHeapRo, num);
            System.out.println(findMedian(maxHeapLo, minHeapRo));
        }
    }

    public static void addNum(PriorityQueue<Integer> maxHeapLo,
                       PriorityQueue<Integer> minHeapRo, int num) {
        maxHeapLo.offer(num);
        minHeapRo.offer(maxHeapLo.poll());
        if(minHeapRo.size() - maxHeapLo.size() == 1){
            maxHeapLo.offer(minHeapRo.poll());
        }
    }

    public static double findMedian(PriorityQueue<Integer> maxHeapLo,
                             PriorityQueue<Integer> minHeapRo) {
        if(maxHeapLo.size() == minHeapRo.size()){
            return (maxHeapLo.peek() + minHeapRo.peek()) / 2.0;
        } else {
            return maxHeapLo.peek();
        }
    }
}
