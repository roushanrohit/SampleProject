package org.dp;

public class MinimumCostPath {

    public static void main(String[] args) {

        int[][] matrix = {{1,2,4,8},{9,5,1,6},{3,2,4,5},{1,9,9,1}};
        System.out.println("Minimum cost to travel from first cell to last cell is : " + minCostDp(matrix));
        System.out.println("Minimum cost to travel from first cell to last cell is : "
                + minCost(matrix, matrix.length - 1, matrix[0].length - 1));
    }

    private static int minCostDp(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] storage = new int[m][n];
        storage[0][0] = matrix[0][0];
        for(int i = 1; i < m; i++) storage[i][0] = storage[i - 1][0] + matrix[i][0];
        for(int j = 1; j < n; j++) storage[0][j] = storage[0][j - 1] + matrix[0][j];

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                storage[i][j] = matrix[i][j] + Math.min(storage[i - 1][j - 1],
                        Math.min(storage[i][j - 1], storage[i - 1][j]));
            }
        }

        return storage[m - 1][n - 1];
    }

    private static int minCost(int[][] matrix, int i, int j) {

        if(i == 0 && j == 0) return matrix[i][j];
        if(i == 0){
            int minCost = 0;
            for(int k = 0; k <= j; k++){
                minCost += matrix[i][k];
            }
            return minCost;
        }
        if(j == 0){
            int minCost = 0;
            for(int k = 0; k <= i; k++){
                minCost += matrix[k][j];
            }
            return minCost;
        }

        return matrix[i][j] + Math.min(minCost(matrix, i - 1, j - 1),
                Math.min(minCost(matrix, i - 1, j), minCost(matrix, i, j - 1)));
    }
}
