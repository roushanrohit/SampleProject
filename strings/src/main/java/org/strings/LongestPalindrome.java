package org.strings;

public class LongestPalindrome {

    public static void main(String[] args) {

        System.out.println(longestPalindrome("babad"));
    }

    private static String longestPalindrome(String s) {

        int maxLength = 0;
        int si = -1;
        int ei = -1;
        for(int i = 0; i < s.length(); i++){
            for(int j = s.length(); j > i; j--){
                int palindromeLength = checkPalindrome(s.substring(i, j));
                if(palindromeLength > 0){
                    // we have found the longest palindrome starting at i
                    if(palindromeLength > maxLength){
                        maxLength = palindromeLength;
                        si = i;
                        ei = j;
                    }
                    break;
                }
            }
        }
        return s.substring(si, ei);
    }

    private static int checkPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while(i < j){
            if(s.charAt(i) != s.charAt(j)){
                return 0;
            }
            i++;
            j--;
        }
        return s.length();
    }
}
