package org.strings;

public class CountPalindromes {

    public static void main(String[] args) {
        String s = "aaa";
        System.out.println("Number of palindromic substrings: " + countPalindromicSubstrings(s));
    }

    public static int countPalindromicSubstrings(String s) {
        if(s == null || s.isEmpty()) return 0;
        int count = 0;

        for(int i = 0; i < s.length(); i++){
            // even length palindromes
            count += expand(s, i, i + 1);
            // odd length palindromes
            count += expand(s, i, i);
        }
        return count;
    }

    // Expands outward from (left, right) as long as characters match.
    private static int expand(String s, int left, int right) {
        int count = 0;
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
            count++;
        }
        return count;
    }
}
