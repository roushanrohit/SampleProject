package misc.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
    Given an array of meeting time intervals where intervals[i] = [starti, endi],
    return the minimum number of conference rooms required.
 */
public class MeetingRooms2 {

    public static void main(String[] args) {

        int[][] intervals = {{0,30},{5,10},{15,20}};
        System.out.print("Meeting Rooms Required: " + meetingRoomsRequired(intervals));
    }

    private static int meetingRoomsRequired(int[][] intervals) {
        if(intervals.length <= 1) return intervals.length;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();

        for(int[] interval : intervals){
            // if the earliest-ending room is free by the time this meeting starts, reuse it
            if(!endTimes.isEmpty() && endTimes.peek() <= interval[0]){
                endTimes.poll();
            }
            endTimes.offer(interval[1]);
        }
        return endTimes.size();
    }
}
