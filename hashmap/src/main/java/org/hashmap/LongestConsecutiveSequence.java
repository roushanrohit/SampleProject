package org.hashmap;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

    public static void main(String[] args) {

        int[] nums = {100,4,200,1,3,2};
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {

        Set<Integer> hset = new HashSet<>();
        for(int num : nums) hset.add(num);

        int maxLength = 0;

        for(int num : hset){
            if(!hset.contains(num - 1)){

                int count = 1;
                while(hset.contains(num + count)){
                    count++;
                }
                maxLength = Math.max(maxLength, count);
            }
        }

        return maxLength;
    }
}
