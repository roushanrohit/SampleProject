package org.slidingwindows.variable;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithKUniqueCharacters {

    public static void main(String[] args) {

        String str = "aabacbebebe";
        int k = 3;
        System.out.print(longestSubstringWithKUniqueCharacters(str, k));
    }

    private static int longestSubstringWithKUniqueCharacters(String s, int k) {

        Map<Character, Integer> hmap = new HashMap<>();
        int left = 0, right = 0, longest = 0;
        while(right < s.length()){

            char r = s.charAt(right);
            hmap.put(r, hmap.getOrDefault(r, 0) + 1);

            while(hmap.size() > k){
                char l = s.charAt(left);
                int freq = hmap.get(l);
                if(freq == 1){
                    hmap.remove(l);
                } else {
                    hmap.put(l, freq - 1);
                }
                left++;
            }

            // we have a substring with k unique characters
            longest = Math.max(longest, right - left + 1);

            right++;
        }
        return longest;
    }
}
