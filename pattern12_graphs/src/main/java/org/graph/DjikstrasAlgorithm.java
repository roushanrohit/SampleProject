package org.graph;

import java.util.Scanner;

public class DjikstrasAlgorithm {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter number of vertices in the graph:");
        int noOfVertices = s.nextInt();
        int[][] graph = new int[noOfVertices][noOfVertices];
        System.out.println("Enter number of edges in the graph:");
        int noOfEdges = s.nextInt();

        // create distances and visited array
        int[] distances = new int[noOfVertices];
        distances[0] = 0;
        for(int i = 1; i < distances.length; i++){
            distances[i] = Integer.MAX_VALUE;
        }
        boolean[] visited = new boolean[noOfVertices];

        for(int i = 0; i < noOfEdges; i++){

            System.out.println("Enter first vertex:");
            int firstVertex = s.nextInt();
            System.out.println("Enter second vertex:");
            int secondVertex = s.nextInt();
            System.out.println("Enter distance:");
            int distance = s.nextInt();

            graph[firstVertex][secondVertex] = distance;
            graph[secondVertex][firstVertex] = distance;
        }

        djikstra(graph, distances, visited);
        System.out.println("Minimum distances of every vertex from 0:");
        for(int i = 1; i < distances.length; i++){
            System.out.println("vertex: " + i + " min distances from 0: " + distances[i]);
        }
    }

    private static void djikstra(int[][] graph, int[] distances, boolean[] visited) {

        for(int i = 0; i < graph.length; i++){
            int minVertex = findMinVertex(distances, visited);
            visited[minVertex] = true;
            for(int j = 0; j < graph.length; j++){
                if(!visited[j] && graph[minVertex][j] > 0){

                    if(distances[j] > graph[minVertex][j] + distances[minVertex]){
                        distances[j] = graph[minVertex][j] + distances[minVertex];
                    }
                }
            }
        }
    }

    private static int findMinVertex(int[] distances, boolean[] visited) {
        int minVertex = -1;
        for(int i = 0; i < distances.length; i++){
            if(!visited[i] && ((minVertex == -1) || distances[i] < distances[minVertex])){
                minVertex = i;
            }
        }
        return minVertex;
    }
}