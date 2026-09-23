package stack;

import java.util.Arrays;
import java.util.Stack;

/*
    You are given an array where each element represents the height of the
    rectangle at that index. Width of every rectangle is 1 unit.
    You need to find the area of the largest rectangle present in the histogram

    Solution: The idea is to find the best possible rectangle for each index, which will
              be found by extending the rectangle in both directions.
              Till where we can extend it -- until we encounter a smaller height
              We'll use a monotonic stack to find the next and prev smaller heights for each index.

              And, we maintain a global maximum for largest rectangle which will give us the ans.
 */
public class LargestRectangleHistogram {

    public static void main(String[] args) {

        int[] arr = {2, 1, 5, 6, 2, 3};
        System.out.println("Area of largest rectangle: " + largestRectangleHistogram(arr));
    }

    private static int largestRectangleHistogram(int[] arr) {

        int n = arr.length;
        // keep two arrays for storing the indices of next and prev smaller elements
        int[] next = new int[n];
        int[] prev = new int[n];
        Arrays.fill(next, n);
        Arrays.fill(prev, -1);

        // next : {1, 6, 4, 4, 6, 6}
        Stack<Integer> stack = new Stack<>();
        for(int i = n - 1; i >= 0; i--){
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                next[i] = stack.peek();
            }
            stack.push(i);
        }
        while(!stack.isEmpty()) {
            stack.pop();
        }
        // prev : {-1, -1, 1, 2, 1, 4}
        for(int i = 0; i < n; i++){
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                prev[i] = stack.peek();
            }
            stack.push(i);
        }

        for(int num : next){
            System.out.print(num + " ");
        }
        System.out.println();
        for(int num : prev){
            System.out.print(num + " ");
        }
        System.out.println();

        int maxArea = 0;
        for(int i = 0; i < n; i++){
            int nextSmallerIndex = next[i];
            int prevSmallerIndex = prev[i];
            maxArea = Math.max(maxArea, arr[i] * (nextSmallerIndex - prevSmallerIndex - 1));
        }

        return maxArea;
    }
}
