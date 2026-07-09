package org.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {

        //System.out.println(longestSubstringWithoutRepeatingCharacters("abcabcbb"));
        //System.out.println(longestSubstringWithoutRepeatingCharacters3("abcabcbb"));

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
                chars.put(l, chars.get(l) - 1);
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


    private static int longestSubstringWithoutRepeatingCharacters3(String str) {

        int n = str.length();
        int left = 0, right = 0, maxlength = 0;
        Set<Character> hset = new HashSet<>();

        while(right < n){

            // remove characters until duplicate is gone
            while (hset.contains(str.charAt(right))) {
                hset.remove(str.charAt(left));
                left++;
            }

            // add current character
            hset.add(str.charAt(right));

            // update answer
            maxlength = Math.max(maxlength, right - left + 1);
            right++;
        }
        return maxlength;
    }


    // sliding window
    private static String longestSubstringWithoutRepeatingCharacters2(String str) {

        int n = str.length();
        int left = 0, right = 0;
        Set<Character> hset = new HashSet<>();

        // first window
        while(right < n){
            if(hset.contains(str.charAt(right))){
                break;
            } else {
                hset.add(str.charAt(right));
            }
            right++;
        }

        int maxLength = right;
        int si = 0;

        // slide the window
        while(right < n){

            // remove characters until duplicate is gone
            while (hset.contains(str.charAt(right))) {
                hset.remove(str.charAt(left));
                left++;
            }

            // add current character
            hset.add(str.charAt(right));

            // update answer
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                si = left;
            }

            right++;
        }

        return str.substring(si, si + maxLength);
    }

    // brute force
    private static String longestSubstringWithoutRepeatingCharacters(String str) {

        int k = 0;
        int n = str.length();
        String[] arr = new String[(n * (n + 1))/2];
        for(int i = 0; i < str.length(); i++){
            for(int j = i + 1; j <= str.length(); j++){
                arr[k++] = str.substring(i, j);
            }
        }
        int maxLength = 0;
        String maxLengthSubstring = "";
        for(String s : arr){
            Set<Character> set = new HashSet<>();
            int i = 0;
            while(i < s.length()){
                if(set.contains(s.charAt(i))){
                    break;
                }
                set.add(s.charAt(i));
                i++;
            }
            if(i == s.length()){
                // no repeating characters
                if(s.length() > maxLength){
                    maxLength = s.length();
                    maxLengthSubstring = s;
                }
            }
        }

        return maxLengthSubstring;
    }
}