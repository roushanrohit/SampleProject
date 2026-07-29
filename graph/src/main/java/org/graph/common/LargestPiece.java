package org.graph.common;

public class LargestPiece {

    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    public static void main(String[] args) {

        int[][] grid = new int[][]{{0,1,0,0,1},{1,0,0,0,0},{0,0,0,0,1},{0,0,0,0,1},{1,0,1,1,0}};
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int largestPiece = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int size = dfs(grid, i, j, visited, m, n);
                largestPiece = Math.max(largestPiece, size);
            }
        }
        System.out.println("Largest Piece: " + largestPiece);
    }

    private static int dfs(int[][] grid, int i, int j, boolean[][] visited, int m, int n) {

        if(i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || grid[i][j] == 0) return 0;
        visited[i][j] = true;
        int size = 1;
        for(int[] d : dir){
            size += dfs(grid, i + d[0], j + d[1], visited, m, n);
        }
        return size;
    }
}
