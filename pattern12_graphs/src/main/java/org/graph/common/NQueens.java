package org.graph.common;

import java.util.*;

public class NQueens {

    public static void main(String[] args) {

        // total number of ways to place n queens in a n*n board
        List<List<String>> result = new ArrayList<>();

        // Board
        int n = 4;
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        // Stores columns where queens are already placed
        Set<Integer> columns = new HashSet<>();

        // Stores (row - col) values for main diagonals
        Set<Integer> diagonal1 = new HashSet<>();

        // Stores (row + col) values for anti-diagonals
        Set<Integer> diagonal2 = new HashSet<>();

        populateWaysToPlaceNQueens(0, n, board, columns, diagonal1, diagonal2, result);

        // print the result
        System.out.println(result.size());
        for(List<String> row : result){
            System.out.println(row);
        }

        // print the board ... it should be empty
        System.out.println("Printing the board .. it should be empty");
        for (char[] r : board) {
            System.out.println(r);
        }
    }

    private static void populateWaysToPlaceNQueens(int row, int n, char[][] board, Set<Integer> columns,
                                                   Set<Integer> diagonal1, Set<Integer> diagonal2, List<List<String>> result) {

        if (row == n) {
            // All N queens have been placed
            List<String> solution = new ArrayList<>();
            for (char[] r : board) {
                solution.add(new String(r));
            }
            result.add(solution);
        } else {
            // Try placing queen in every column of this row
            for (int col = 0; col < n; col++) {

                // Check if position is safe
                if (columns.contains(col) || diagonal1.contains(row - col) || diagonal2.contains(row + col)) {
                    continue;
                }

                // place the queen
                board[row][col] = 'Q';
                columns.add(col);
                diagonal1.add(row - col);
                diagonal2.add(row + col);

                // Explore
                populateWaysToPlaceNQueens(row + 1, n, board, columns, diagonal1, diagonal2, result);

                // Undo / Backtrack
                board[row][col] = '.';
                columns.remove(col);
                diagonal1.remove(row - col);
                diagonal2.remove(row + col);
            }
        }
    }
}
