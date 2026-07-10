package org.strings;

public class LongestPalindrome2 {

    public static void main(String[] args) {

        System.out.println(longestPalindrome("babad"));
    }

    // expand around the center approach
    private static String longestPalindrome(String s) {

        if(s == null || s.isEmpty()) return s;
        int bestStart = 0, bestEnd = 0;

        for(int i = 0; i < s.length(); i++){

            // odd length palindromes
            int[] odd = expand(s, i, i);
            if(odd[1] - odd[0] > bestEnd - bestStart){
                bestStart = odd[0];
                bestEnd = odd[1];
            }

            // even length palindromes
            int[] even = expand(s, i, i+1);
            if(even[1] - even[0] > bestEnd - bestStart){
                bestStart = even[0];
                bestEnd = even[1];
            }
        }
        return s.substring(bestStart, bestEnd + 1);
    }

    // Expands outward from (left, right) as long as characters match.
    // Returns the final [left, right] bounds of the palindrome found.
    private static int[] expand(String s, int left, int right) {

        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }

        // At this point, left/right are one step past the actual palindrome bounds
        // (either out of range, or characters didn't match), so step back in.
        return new int[]{left + 1, right - 1};
    }
}
