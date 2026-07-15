package misc;

/*
    There is a robot on an m x n grid. The robot is initially located in the top-left corner (i.e., grid[0][0]).
    The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
    The robot can only move either down or right at any point in time.
    Given the two integers m and n, return the number of possible unique paths that the robot can take
    to reach the bottom-right corner.
 */
public class UniquePaths {

    public static void main(String[] args) {

        System.out.println("Unique paths for a matrix of size 10, 20 are: " + uniquePaths(10, 20));
    }

    public static int uniquePaths(int m, int n) {

        int[][] matrix = new int[m][n];
        for(int i = m - 2; i >= 0; i--){
            matrix[i][n - 1] = 1;
        }
        for(int j = n - 2; j >= 0; j--){
            matrix[m - 1][j] = 1;
        }
        matrix[m - 1][n - 1] = 1;

        for(int i = m - 2; i >= 0; i--){
            for(int j = n - 2; j >= 0; j--){
                matrix[i][j] = matrix[i][j + 1] + matrix[i + 1][j];
            }
        }
        return matrix[0][0];
    }
}
