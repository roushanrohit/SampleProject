package org.slidingwindow.variablesizewindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithKUniqueCharacters {

    public static void main(String[] args) {

        String str = "aabacbebebe";
        int k = 3;
        System.out.print(longestSubstringWithKUniqueCharacters(str, k));
    }

    private static String longestSubstringWithKUniqueCharacters(String str, int k) {

        int n = str.length();
        int left = 0, right = 0;
        Map<Character, Integer> hmap = new HashMap<>();

        // first window
        while(right < n && hmap.size() < k){
            if(hmap.containsKey(str.charAt(right))){
                hmap.put(str.charAt(right), hmap.get(str.charAt(right)) + 1);
            } else {
                hmap.put(str.charAt(right), 1);
            }
            right++;
        }

        int maxLength = 0;
        int si = -1;
        if(hmap.size() == k){
            maxLength = right - left;
            si = left;
        }

        // slide the window
        while(right < n){

            if(hmap.containsKey(str.charAt(right))){
                hmap.put(str.charAt(right), hmap.get(str.charAt(right)) + 1);
            } else {
                hmap.put(str.charAt(right), 1);
            }

            while(hmap.size() > k){
                Integer count = hmap.get(str.charAt(left));
                if(count == 1){
                    hmap.remove(str.charAt(left));
                } else {
                    hmap.put(str.charAt(left), count - 1);
                }
                left++;
            }

            if(hmap.size() == k){
                if(right - left + 1 > maxLength){
                    maxLength = right - left + 1;
                    si = left;
                }
            }
            right++;
        }
        return str.substring(si, si + maxLength);
    }
}
