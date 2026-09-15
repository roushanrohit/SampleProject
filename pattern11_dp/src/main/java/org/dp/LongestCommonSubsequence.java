package org.dp;

public class LongestCommonSubsequence {

    public static void main(String[] args) {

        String s = "adgei";
        String t = "abegi";
        System.out.println("Longest common subsequence of " + s + " and " + t + " is: " + longestCommonSubsequenceDp(s, t));
        System.out.println("Longest common subsequence of " + s + " and " + t + " is: " + longestCommonSubsequenceDp2(s, t));
        System.out.println("Longest common subsequence of " + s + " and " + t + " is: " + longestCommonSubsequence(s, t));
        System.out.println("Longest common subsequence of " + s + " and " + t + " is: " + longestCommonSubsequence2(s, t));

    }

    private static int longestCommonSubsequenceDp(String s, String t) {

        int m = s.length();
        int n = t.length();
        int[][] storage = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(s.charAt(m - i) == t.charAt(n - j)){
                    storage[i][j] = 1 + storage[i - 1][j - 1];
                } else {
                    storage[i][j] = Math.max(storage[i][j - 1], storage[i - 1][j]);
                }
            }
        }
        return storage[m][n];
    }

    private static String longestCommonSubsequenceDp2(String s, String t) {

        int m = s.length();
        int n = t.length();
        String[][] storage = new String[m + 1][n + 1];
        storage[0][0] = "";
        for(int i = 1; i <= m; i++) storage[i][0] = "";
        for(int j = 1; j <= n; j++) storage[0][j] = "";
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(s.charAt(m - i) == t.charAt(n - j)){
                    storage[i][j] = s.charAt(m - i) + storage[i - 1][j - 1];
                } else {
                    if(storage[i - 1][j].length() > storage[i][j - 1].length()){
                        storage[i][j] = storage[i - 1][j];
                    } else {
                        storage[i][j] = storage[i][j - 1];
                    }
                }
            }
        }
        return storage[m][n];
    }

    private static int longestCommonSubsequence(String s, String t) {

        if(s.isEmpty() || t.isEmpty()) return 0;

        if(s.charAt(0) == t.charAt(0)) return 1 + longestCommonSubsequence(s.substring(1), t.substring(1));

        int op1 = longestCommonSubsequence(s, t.substring(1));
        int op2 = longestCommonSubsequence(s.substring(1), t);
        return Math.max(op1, op2);
    }

    private static String longestCommonSubsequence2(String s, String t) {

        if(s.isEmpty() || t.isEmpty()) return "";

        if(s.charAt(0) == t.charAt(0)) return s.charAt(0) + longestCommonSubsequence2(s.substring(1), t.substring(1));

        String op1 = longestCommonSubsequence2(s, t.substring(1));
        String op2 = longestCommonSubsequence2(s.substring(1), t);
        return op1.length() > op2.length() ? op1 : op2;
    }
}
