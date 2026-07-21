package misc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
    Given a string s and a dictionary of strings wordDict, return true if s can be segmented into
    a space-separated sequence of one or more dictionary words.
    Note that the same word in the dictionary may be reused multiple times in the segmentation.
 */
public class WordBreak {

    public static void main(String[] args) {

        String s = "applepenapple";
        List<String> wordDict = List.of("apple", "pen");
        System.out.println(wordBreak(s, wordDict));
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        return wordBreak(s, new HashSet<>(wordDict), new Boolean[s.length()], 0);
    }

    public static boolean wordBreak(String s, Set<String> hset, Boolean[] memo, int si) {

        // base case
        if(si == s.length()){
            return true;
        }
        if(memo[si] != null){
            return memo[si];
        }

        for(int ei = si + 1; ei <= s.length(); ei++){
            if(hset.contains(s.substring(si, ei)) && wordBreak(s, hset, memo, ei)){
                memo[si] = true;
                return memo[si];
            }
        }

        memo[si] = false;
        return memo[si];
    }
}
