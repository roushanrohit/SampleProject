package misc;

import java.util.Arrays;
import java.util.Comparator;

/*
    You are given an array of meeting times intervals where intervals[i] = [starti, endi].
    A person can attend all meetings if no two meeting intervals overlap.
    Meetings ending at time t and starting at time t do not overlap.
    Return true if a person can attend all meetings. Otherwise, return false.
 */
public class MeetingRooms {

    public static void main(String[] args) {
        int[][] intervals = {{0,30},{5,10},{15,20}};
        System.out.print("Can attend all meetings: " + canAttendMeetings(intervals));
    }

    public static boolean canAttendMeetings(int[][] intervals) {
        if(intervals.length <= 1) return true;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[] current = intervals[0];
        for(int i = 1; i < intervals.length; i++){
            int[] next = intervals[i];
            if(next[0] < current[1]){
                // overlap exists
                return false;
            } else {
                current = next;
            }
        }
        return true;
    }
}
