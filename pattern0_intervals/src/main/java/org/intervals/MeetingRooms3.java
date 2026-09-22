package org.intervals;

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

    private static int intervalScheduling(int[][] intervals){

        //sort by end times
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int[] current = intervals[0];
        int meetings = 1;
        for(int i = 1; i < intervals.length; i++){
            int[] next = intervals[i];
            if(next[0] >= current[1]){
                meetings++;
                current = next;
            }
        }
        return meetings;
    }
}
