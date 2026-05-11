package org.graph;

import java.util.Scanner;

public class PrimsAlgorithm {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter number of vertices in the graph:");
        int noOfVertices = s.nextInt();
        int[][] graph = new int[noOfVertices][noOfVertices];
        System.out.println("Enter number of edges in the graph:");
        int noOfEdges = s.nextInt();

        // create weights, visited and parents array
        int[] weights = new int[noOfVertices];
        weights[0] = 0;
        for(int i = 1; i < weights.length; i++){
            weights[i] = Integer.MAX_VALUE;
        }
        int[] parents = new int[noOfVertices];
        parents[0] = -1;
        boolean[] visited = new boolean[noOfVertices];

        for(int i = 0; i < noOfEdges; i++){

            System.out.println("Enter first vertex:");
            int firstVertex = s.nextInt();
            System.out.println("Enter second vertex:");
            int secondVertex = s.nextInt();
            System.out.println("Enter weight:");
            int weight = s.nextInt();

            graph[firstVertex][secondVertex] = weight;
            graph[secondVertex][firstVertex] = weight;
        }

        prims(graph, weights, visited, parents);
        System.out.println("MST by Prim's Algorithm:");
        for(int i = 1; i < parents.length; i++){
            System.out.println("startVertex: " + i + " endVertex: " + parents[i] + " weights: " + weights[i]);
        }
    }

    private static void prims(int[][] graph, int[] weights, boolean[] visited, int[] parents) {

        for(int i = 0; i < graph.length; i++){
            int minVertex = findMinVertex(weights, visited);
            visited[minVertex] = true;
            for(int j = 0; j < graph.length; j++){
                if(!visited[j] && graph[minVertex][j] > 0){

                    if(weights[j] > graph[minVertex][j]){
                        weights[j] = graph[minVertex][j];
                        parents[j] = minVertex;
                    }
                }
            }
        }
    }

    private static int findMinVertex(int[] weights, boolean[] visited) {
        int minVertex = -1;
        for(int i = 0; i < weights.length; i++){
            if(!visited[i] && ((minVertex == -1) || weights[i] < weights[minVertex])){
                minVertex = i;
            }
        }
        return minVertex;
    }

}