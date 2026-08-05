package org.strings;

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

        int[] count = new int[26];
        int left = 0;
        int maxFreq = 0;
        int maxLen = 0;

        for(int right = 0; right < s.length(); right++){
            char r = s.charAt(right);
            count[r - 'A']++;
            maxFreq = Math.max(maxFreq, count[r - 'A']);

            /*
                window size - most frequent char count in the window = chars needing replacement
                and if it is < k, we have a valid window
             */
            while((right - left + 1) - maxFreq > k){
                char l = s.charAt(left);
                count[l - 'A']--;
                left++;
            }
            // we have a valid window now
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
