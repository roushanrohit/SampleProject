package misc;

public class RotateImage {

    public static void main(String[] args) {

        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("Original Matrix: ");
        printMatrix(matrix);

        System.out.println("Matrix Rotated 90 degrees clockwise: ");
        rotateClockwise90(matrix);
        printMatrix(matrix);

        System.out.println("Matrix Rotated 90 degrees anticlockwise: ");
        rotate90AntiClockwise(matrix);
        printMatrix(matrix);

        System.out.println("Matrix Rotated 180 degrees clockwise: ");
        rotateClockwise180(matrix);
        printMatrix(matrix);
    }

    private static void rotate90AntiClockwise(int[][] matrix) {

        // transpose the matrix ... rows become columns and columns becomes rows
        transpose(matrix);

        // reverse it column-wise
        int n = matrix.length;
        int m = matrix[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n/2; j++){
                int temp = matrix[j][i];
                matrix[j][i] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = temp;
            }
        }
    }

    private static void rotateClockwise180(int[][] matrix) {

        int n = matrix.length;
        // reverse the rows
        for(int i = 0; i < n/2; i++){
            int[] temp = matrix[i];
            matrix[i] = matrix[n - i - 1];
            matrix[n - i - 1] = temp;
        }

        // reverse it row-wise
        int m = matrix[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m/2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][m - j - 1];
                matrix[i][m - j - 1] = temp;
            }
        }
    }

    private static void rotateClockwise90(int[][] matrix) {

        // transpose the matrix ... rows become columns and columns becomes rows
        transpose(matrix);

        // reverse it row-wise
        int n = matrix.length;
        int m = matrix[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m/2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][m - j - 1];
                matrix[i][m - j - 1] = temp;
            }
        }

    }

    private static void transpose(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = i + 1; j < matrix[0].length; j++){
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
