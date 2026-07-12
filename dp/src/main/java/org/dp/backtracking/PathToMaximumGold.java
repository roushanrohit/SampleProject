package org.dp.backtracking;

/*
    You are given an m x n grid.

    Each cell contains some gold (> 0) or 0.
    You can start from any cell containing gold.
    From a cell, you can move up, down, left, or right.
    You cannot visit the same cell twice in a path.
    You cannot step on a cell with 0 gold.
    Return the maximum gold that can be collected.

    Can we use DP?
    No. Why?
    Suppose you're standing at (1,1). The answer from (1,1) depends on which cells have already been visited.
    Example:
    1 2
    3 4
    If you reach 4 after visiting {1,2} remaining choices are different than reaching 4 after visiting {3}.
    So the state isn't just (row, col). It is (row, col, visitedCells)
    Also there are 2^(m*n) possible visited states,
 */
public class PathToMaximumGold {

    static int[][] dir = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    public static void main(String[] args) {

        int[][] grid = {{1,2,3},{0,5,0},{7,8,9}};
        int maxGoldCollected = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                maxGoldCollected = Math.max(maxGoldCollected, dfsMaxGoldCollected(grid, i, j));
            }
        }
        System.out.println("Max Gold Collected : " + maxGoldCollected);
    }

    private static int dfsMaxGoldCollected(int[][] grid, int i, int j) {

        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length) {
            return 0;
        }
        if (grid[i][j] == 0) {
            return 0;
        }

        int gold = grid[i][j];
        grid[i][j] = 0;

        int maxGoldCollectedFromNeighbours = 0;
        for(int[] d : dir){
            maxGoldCollectedFromNeighbours = Math.max(maxGoldCollectedFromNeighbours,
                    dfsMaxGoldCollected(grid, i + d[0], j + d[1]));
        }

        // recursion done -- restore state
        grid[i][j] = gold;

        return gold + maxGoldCollectedFromNeighbours;
    }
}
