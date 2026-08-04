package misc;

import java.util.Arrays;

public class MaxNonOverlappingIntervals {

    public static void main(String[] args) {

        int[][] intervals = {{1,2},{2,3},{3,4},{1,3}};
        // Maximum number of non-overlapping intervals
        System.out.println("Minimum intervals to remove to make it non-overlapping: " + maxNonOverlappingIntervals(intervals));
    }

    public static int maxNonOverlappingIntervals(int[][] intervals) {
        int count = 0;
        // sort the intervals by their start time
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        int[] current = intervals[0];
        for(int i = 1; i < intervals.length; i++){
            int[] next = intervals[i];
            if(next[0] < current[1]){
                // next interval starts before current ends -- overlapping
                count++;
                if(current[1] < next[1]){
                    // if current ends early, simply ignore the next
                    continue;
                }
            }
            current = next;
        }
        return count;
    }
}
