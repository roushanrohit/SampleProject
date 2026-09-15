package org.graph;

import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Scanner;

public class SpanningTrees {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter number of vertices in the graph:");
        int noOfVertices = s.nextInt();
        int[][] graph = new int[noOfVertices][noOfVertices];
        System.out.println("Enter number of edges in the graph:");
        int noOfEdges = s.nextInt();

        for(int i = 0; i < noOfEdges; i++){

            System.out.println("Enter first vertex:");
            int firstVertex = s.nextInt();
            System.out.println("Enter second vertex:");
            int secondVertex = s.nextInt();

            graph[firstVertex][secondVertex] = 1;
            graph[secondVertex][firstVertex] = 1;
        }

        System.out.println("Number of spanning trees possible for the graph is: " + noOfSpanningTrees(graph));
    }

    private static int noOfSpanningTrees(int[][] graph) {

        int n = graph.length;

        // build the laplacian matrix
        int[][] lap = new int[n][n];
        for(int i = 0; i < n; i++){
            int degree = 0;
            for(int j = 0; j < n; j++){
                if(graph[i][j] == 1) degree++;
            }
            for(int j = 0; j < n; j++){
                if(i == j) lap[i][j] = degree;
                else lap[i][j] = -1 * graph[i][j];
            }
        }

        // construct l' by removing last row and last column
        int size = n - 1;
        double[][] mat = new double[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                mat[i][j] = lap[i][j];
            }
        }

        RealMatrix m = MatrixUtils.createRealMatrix(mat);
        LUDecomposition lu = new LUDecomposition(m);
        return (int) lu.getDeterminant();
    }
}