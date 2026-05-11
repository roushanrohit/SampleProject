package org.slidingwindow.fixedsizewindow;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FirstNegativeNumberInEveryWindow {

    public static void main(String[] args) {

        int[] arr = new int[]{12,-1,-7,8,-18,30,16,28};
        int k = 3;
        List<Integer> firstNegativeNumberInEveryWindow = firstNegativeNoInEveryWindow(arr, k);
        System.out.println(firstNegativeNumberInEveryWindow);
    }

    private static List<Integer> firstNegativeNoInEveryWindow(int[] arr, int k){

        int n = arr.length;
        if(n < k) return new ArrayList<>();

        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        // first window
        for(int i = 0; i < k; i++){
            if(arr[i] < 0) queue.add(arr[i]);
        }
        result.add(!queue.isEmpty() ? queue.peek() : 0);

        // slide the window
        for(int end = k; end < n; end++){

            // element moving in
            if(arr[end] < 0) queue.add(arr[end]);
            // element moving out
            if(arr[end - k] < 0) queue.poll();

            result.add(!queue.isEmpty() ? queue.peek() : 0);
        }

        return result;
    }
}
