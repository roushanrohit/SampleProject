package misc.intervals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
    You are given a list of lamps positions and the illuminating radius
    eg: [-2,3] means it can illuminate from [-2-3,-2+3] = [-5,1]
    You need to return the count of integers on the number line that are illuminated by exactly one lamp
 */
public class IlluminatingLamps {

    public static void main(String[] args) {

        int[][] lamps = {{-2,3},{2,3},{2,1}};
        System.out.println("Number of indices illuminated by exactly one lamp : " + illuminatingLamps(lamps));
    }

    public static int illuminatingLamps(int[][] lamps){

        List<int[]> events = new ArrayList<>();
        for(int[] lamp : lamps){
            events.add(new int[]{lamp[0] - lamp[1], 1});
            events.add(new int[]{lamp[0] + lamp[1], -1});
        }
        // sort the events
        events.sort(Comparator.comparingInt((int[] a) -> a[0]).thenComparing(a -> a[1]));

        int active = 0;
        int previous = events.get(0)[0];
        int ans = 0;
        for(int[] event : events){
            int position = event[0];
            if(active == 1){
                ans += (position - previous);
            }
            active += event[1];
            previous = position;
        }

        return ans;
    }
}
