package misc;

import java.util.ArrayList;
import java.util.List;

/*
    Given a list of non overlapping intervals and a new interval, insert it and merge if necessary
    eg: [[1,3],[6,9]]
        [2,5]
        Result: [[1,5],[6,9]]

    One way to solve it is to just add the new interval to the intervals array and then run the merge intervals algorithm
    But, it is mentioned in the question that the existing intervals are already sorted and are non-overlapping
    So, we can actually solve it in O(n):
    1. Copy all intervals ending before the new interval starts
    2. Merge all overlapping intervals with the new interval
    3. Copy the remaining intervals

 */
public class InsertInterval {

    public static void main(String[] args) {

        int[][] intervals = {{1,3},{6,9}};
        int[] newInterval = {2,5};
        List<int[]> result = addInterval(intervals, newInterval);
        for(int[] interval : result) {
            System.out.println(interval[0] + " " + interval[1]);
        }
    }

    private static List<int[]> addInterval(int[][] intervals, int[] newInterval) {

        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // intervals completely before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // merge overlapping intervals with newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        result.add(newInterval);

        // intervals completely after newInterval
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result;
    }
}
