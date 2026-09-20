package org.slidingwindows.variable;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    private static String lengthOfLongestSubstring(String s) {

        Map<Character, Integer> chars = new HashMap<>();
        int left = 0;
        int right = 0;
        int res = 0;
        String ans = "";

        while(right < s.length()){

            char r = s.charAt(right);
            chars.put(r, chars.getOrDefault(r, 0) + 1);

            while(chars.get(r) > 1){
                char l = s.charAt(left);
                int freq = chars.get(l);
                if(freq == 1){
                    chars.remove(l);
                } else {
                    chars.put(l, freq - 1);
                }
                left++;
            }

            // we have a substring with no repetitive characters
            if((right - left + 1) >  res){
                res = (right - left + 1);
                ans = s.substring(left, right + 1);
            }

            right++;
        }

        return ans;
    }
}