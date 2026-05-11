package org.slidingwindow.fixedsizewindow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MaximumOfAllSubArraysOfSizeK {

    public static void main(String[] args) {

        int[] arr = new int[]{1,12,-5,-6, 50, 3};
        int k = 4;
        List<Integer> maximumOfAllSubArraysOfSizeK = maximumSubArraySizeK(arr, k);
        System.out.println(maximumOfAllSubArraysOfSizeK);
    }

    private static List<Integer> maximumSubArraySizeK(int[] arr, int k) {

        int n = arr.length;
        if(n < k) return new ArrayList<>(); // edge case

        List<Integer> maximumOfAllSubArraysOfSizeK = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();

        // first window
        for(int i = 0; i < k; i++){

            // pop from back all the elements smaller than arr[i]
            while(!deque.isEmpty() && deque.peekLast() < arr[i]){
                deque.pollLast();
            }
            deque.add(arr[i]);
        }

        // add result for first window
        maximumOfAllSubArraysOfSizeK.add(deque.peekFirst());

        // slide the window
        for(int end = k; end < n; end++){

            // check if maximum element is the outgoing element
            if(!deque.isEmpty() && arr[end - k] == deque.peekFirst()){
                deque.pollFirst();
            }
            // pop from back all the elements smaller than arr[end]
            while(!deque.isEmpty() && deque.peekLast() < arr[end]){
                deque.pollLast();
            }
            deque.add(arr[end]);

            // add result for the current window
            maximumOfAllSubArraysOfSizeK.add(deque.peekFirst());
        }

        return maximumOfAllSubArraysOfSizeK;
    }
}
