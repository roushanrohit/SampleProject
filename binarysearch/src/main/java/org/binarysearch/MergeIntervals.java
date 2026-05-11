package org.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    public static void main(String[] args) {

        int[][] intervals = {{1,3},{2,6},{15,18},{8,10}};
        List<int[]> mergedIntervals = mergeIntervals(intervals);
        for(int[] interval : mergedIntervals) {
            System.out.println(interval[0] + " " + interval[1]);
        }
    }

    private static List<int[]> mergeIntervals(int[][] intervals) {

        // intervals overlap when currentstart < previousend
        // sort by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> result = new ArrayList<>();
        int[] current = intervals[0];
        for(int i = 1; i < intervals.length; i++){

            int[] next = intervals[i];
            // overlap
            if(next[0] <= current[1]){
                current[1] = Math.max(current[1], next[1]);
            } else {
                // no overlap
                result.add(current);
                current = next;
            }
        }
        // add last interval
        result.add(current);

        return result;
    }
}
