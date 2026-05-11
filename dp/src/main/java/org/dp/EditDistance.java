package org.dp;

public class EditDistance {

    public static void main(String[] args) {

        String s = "adgei";
        String t = "abegi";
        System.out.println("Longest common subsequence of " + s + " and " + t + " is: " + editDistanceDp(s, t));
        System.out.println("Longest common subsequence of " + s + " and " + t + " is: " + editDistance(s, t));
    }

    private static int editDistanceDp(String s, String t) {

        int m = s.length();
        int n = t.length();
        int[][] storage = new int[m + 1][n + 1];
        storage[0][0] = 0;
        for(int i = 1; i <= m; i++) storage[i][0] = i;
        for(int j = 1; j <= n; j++) storage[0][j] = j;
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(s.charAt(m - i) == t.charAt(n - j)){
                    storage[i][j] = storage[i - 1][j - 1];
                } else {
                    storage[i][j] = 1 + Math.min(storage[i - 1][j - 1], Math.min(storage[i][j - 1], storage[i - 1][j]));
                }
            }
        }
        return storage[m][n];
    }

    private static int editDistance(String s, String t) {

        if(s.isEmpty()) return t.length();
        if(t.isEmpty()) return s.length();

        if(s.charAt(0) == t.charAt(0)){
            return editDistance(s.substring(1), t.substring(1));
        } else {
            // insertion
            int op1 = editDistance(s, t.substring(1));
            // substitution
            int op2 = editDistance(s.substring(1), t.substring(1));
            // delete
            int op3 = editDistance(s.substring(1), t);
            return 1 + Math.min(op1, Math.min(op2, op3));
        }
    }
}
