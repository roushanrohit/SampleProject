package misc.intervals;

import java.util.*;

/*
    Given an array of meeting time intervals where intervals[i] = [starti, endi],
    return the minimum number of conference rooms required.
    sweep line problem
 */
public class MeetingRooms2 {

    public static void main(String[] args) {

        int[][] intervals = {{0,30},{5,10},{15,20}};
        System.out.print("Meeting Rooms Required: " + meetingRoomsRequired(intervals));
    }

    private static int meetingRoomsRequired(int[][] intervals){
        List<int[]> events = new ArrayList<>();
        for(int[] interval : intervals){
            events.add(new int[]{interval[0], 1});
            events.add(new int[]{interval[1], -1});
        }
        events.sort(Comparator.comparingInt((int[] a) -> a[0]).thenComparing(a -> a[1]));

        int activeMeetingRooms = 0;
        int meetingRoomsNeeded = 0;
        for(int[] event : events){
            activeMeetingRooms += event[1];
            meetingRoomsNeeded = Math.max(activeMeetingRooms, meetingRoomsNeeded);
        }
        return meetingRoomsNeeded;
    }
}
