package org.slidingwindows.variable;

import java.util.HashMap;
import java.util.Map;

/*
    You are given a string s and an integer k. You can choose any character of the string and change it to any
    other uppercase English character. You can perform this operation at most k times.
    Return the length of the longest substring containing the same letter you can get after performing the above
    operations.
 */
public class LongestRepeatingCharacterReplacement {

    public static void main(String[] args) {
        String s = "ABBBB";
        int k = 1;
        System.out.println(longestRepeatingCharacterReplacement(s, k));
    }

    public static int longestRepeatingCharacterReplacement(String s, int k){

        Map<Character, Integer> hmap = new HashMap<>();
        int left = 0, right = 0, longest = 0;
        int maxFreq = 0;
        while(right < s.length()){

            char r = s.charAt(right);
            hmap.put(r, hmap.getOrDefault(r, 0) + 1);
            maxFreq = Math.max(maxFreq, hmap.get(r));

            if((right - left + 1) - maxFreq > k){
                char l = s.charAt(left);
                int freq = hmap.get(l);
                if(freq == 1){
                    hmap.remove(l);
                } else {
                    hmap.put(l, freq - 1);
                }
                left++;
            }

            longest = Math.max(longest, right - left + 1);

            right++;
        }
        return longest;
    }
}
