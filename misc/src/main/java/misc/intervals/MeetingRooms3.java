package misc.intervals;

import java.util.Arrays;
import java.util.Comparator;

/*
   Given the list of meeting intervals, we need to find out the maximum meetings one can attend
   Core Idea: choose the interval that end early
 */
public class MeetingRooms3 {

    public static void main(String[] args) {

        int[][] intervals = {{0,30},{5,10},{15,20}};
        System.out.print("Maximum meetings I can attend: " + intervalScheduling(intervals));
    }

    private static int intervalScheduling(int[][] intervals) {

        // sort by end times
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int meetingsAttended = 0;
        int lastEnd = Integer.MIN_VALUE;
        for(int[] interval: intervals){
            if(interval[0] >= lastEnd){
                meetingsAttended++;
                lastEnd = interval[1];
            }
        }
        return meetingsAttended;
    }
}
