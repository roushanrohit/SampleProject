package org.oops.basics;

public class SpiralMatrix {

    public static void spiralPrint(int[][] matrix) {

        int rowStart = 0;
        int rowEnd = matrix.length - 1;
        int colStart = 0;
        int colEnd = matrix[0].length - 1;

        while (rowStart <= rowEnd) {
            for (int i = colStart; i <= colEnd; i++) {
                System.out.print(matrix[rowStart][i] + " ");
            }
            rowStart++;

            for (int i = rowStart; i <= rowEnd; i++) {
                System.out.print(matrix[i][colEnd] + " ");
            }
            colEnd--;

            for (int i = colEnd; i >= colStart; i--) {
                System.out.print(matrix[rowEnd][i] + " ");
            }
            rowEnd--;

            for (int i = rowEnd; i >= rowStart; i--) {
                System.out.print(matrix[i][colStart] + " ");
            }
            colStart++;
        }

        System.out.println();
        System.out.println("rowStart: " + rowStart);
        System.out.println("rowEnd: " + rowEnd);
        System.out.println("colStart: " + colStart);
        System.out.println("colEnd: " + colEnd);
    }

    public static void main(String[] args) {

        int[][] matrix = {{1,2,3,4},{14,15,16,5},{13,20,17,6},{12,19,18,7},{11,10,9,8}};
        System.out.println(matrix[0]);
        spiralPrint(matrix);
    }
}
