package misc;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFromDataStream {

    PriorityQueue<Double> maxHeapLo;
    PriorityQueue<Double> minHeapRo;

    public MedianFromDataStream() {
        maxHeapLo = new PriorityQueue<>(Comparator.reverseOrder());
        minHeapRo = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // add to the max heap
        Double d = (double) num;
        maxHeapLo.offer(d);
        minHeapRo.offer(maxHeapLo.poll());
        if(minHeapRo.size() - maxHeapLo.size() == 1){
            maxHeapLo.offer(minHeapRo.poll());
        }
    }

    public double findMedian() {
        if(maxHeapLo.size() == minHeapRo.size()){
            return (maxHeapLo.peek() + minHeapRo.peek()) / 2;
        } else {
            return maxHeapLo.peek();
        }
    }

    public static void main(String[] args) {

        MedianFromDataStream medianFromDataStream = new MedianFromDataStream();
        int[] arr = {41,35,62,5,97,100};
        for(int num : arr){
            medianFromDataStream.addNum(num);
            System.out.println(medianFromDataStream.findMedian());
        }
    }
}
