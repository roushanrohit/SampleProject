package org.dp.backtracking;

public class WordSearch {

    static int[][] dir = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    public static void main(String[] args) {

        char[][] grid = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        System.out.println(wordSearch(grid, word));
    }

    private static boolean wordSearch(char[][] grid, String word) {

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(wordSearch(grid, word, i, j, 0)) return true;
            }
        }
        return false;
    }

    private static boolean wordSearch(char[][] grid, String word, int i, int j, int pos) {

        // found all the characters
        if(pos == word.length()){
            return true;
        }
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length) {
            return false;
        }
        if (!(grid[i][j] == word.charAt(pos))) {
            return false;
        }

        // mark that this character has been visited -- do not visit again
        char ch = grid[i][j];
        // '\0' is the null character. It is a character literal whose Unicode value is 0
        grid[i][j] = '\0';

        for(int[] d : dir){
            if(wordSearch(grid, word, i + d[0], j + d[1], pos + 1)) return true;
        }

        // restore state
        grid[i][j] = ch;

        // word not found
        return false;
    }
}
