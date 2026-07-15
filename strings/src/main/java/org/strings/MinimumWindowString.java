package org.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that
    every character in t (including duplicates) is included in the window.
    If there is no such substring, return the empty string "".
 */
public class MinimumWindowString {

    public static void main(String[] args) {

        String s = "ADOBECODEBANC", t = "ABC";
        System.out.println("Minimum window substring: " + minWindow(s, t));
    }

    public static String minWindow(String s, String t) {

        List<String> allCandidateSubstrings = new ArrayList<>();
        String minLengthSubstring = "";
        Map<Character, Integer> need = new HashMap<>();
        for(char ch : t.toCharArray()){
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }
        int required = t.length();

        int si = 0, ei = 0;
        while(ei < s.length()){
            char right = s.charAt(ei);
            if(need.containsKey(right)){

                if(need.get(right) > 0){
                    required--;
                }
                need.put(right, need.get(right) - 1);

                while(required == 0){
                    if(minLengthSubstring.isEmpty() || (ei - si + 1) < minLengthSubstring.length()){
                        minLengthSubstring = s.substring(si, ei + 1);
                    }
                    allCandidateSubstrings.add(s.substring(si, ei + 1));

                    char left = s.charAt(si);
                    if (need.containsKey(left)) {
                        need.put(left, need.get(left) + 1);

                        // if removing it made us short of that character, the window is no longer valid
                        if (need.get(left) > 0) {
                            required++;
                        }
                    }
                    si++;
                }
            }
            ei++;
        }

        System.out.println(allCandidateSubstrings);
        return minLengthSubstring;
    }


}
