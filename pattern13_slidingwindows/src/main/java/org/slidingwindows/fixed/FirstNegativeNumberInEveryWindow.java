package org.slidingwindows.fixed;

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

        List<Integer> ans = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < k; i++){
            if(arr[i] < 0){
                queue.add(arr[i]);
            }
        }
        ans.add(!queue.isEmpty() ? queue.peek() : 0);
        for(int j = k; j < arr.length; j++){
            if(arr[j] < 0) {
                queue.add(arr[j]);
            }
            if(arr[j - k] < 0) {
                // it means this will be the first element in the queue
                queue.poll();
            }
            ans.add(!queue.isEmpty() ? queue.peek() : 0);
        }
        return ans;
    }
}
