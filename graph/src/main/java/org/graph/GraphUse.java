package org.graph;

import java.util.LinkedList;
import java.util.Queue;

public class GraphUse {
    public static void main(String[] args) {

        int[][] graph = new int[][]{{0,1,0,0,1},{1,0,0,0,0},{0,0,0,0,1},{0,0,0,0,1},{1,0,1,1,0}};
        boolean[] visited = new boolean[graph.length];
        // writing this loop to cover all connected components
        int k = 0;
        for(int i = 0; i < visited.length; i++) {
            if(!visited[i]){
                System.out.println("Printing connected component : " + k++);
                printBFSLevelWise(graph, visited, i);
                System.out.println();
            }
        }
    }

    // prints in dfs fashion
    private static void printDFS(int[][] graph, boolean[] visited, int sv) {

        System.out.print(sv + " ");
        visited[sv] = true;
        for(int i = 0; i < graph.length; i++){
            if(graph[sv][i] == 1 && !visited[i]){
                printDFS(graph, visited, i);
            }
        }
    }

    // prints in bfs fashion
    private static void printBFS(int[][] graph, boolean[] visited, int sv) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(sv);
        visited[sv] = true;
        while(!queue.isEmpty()){

            int front = queue.poll();
            System.out.print(front + " ");
            for (int i = 0; i < graph.length; i++) {
                if (graph[front][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    // prints in bfs fashion level wise
    private static void printBFSLevelWise(int[][] graph, boolean[] visited, int sv) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(sv);
        queue.add(null);
        visited[sv] = true;
        while(!queue.isEmpty()){

            String verticesAtALevel = "";
            while (queue.peek() != null) {
                int front = queue.poll();
                verticesAtALevel += front + " ";
                for (int i = 0; i < graph.length; i++) {
                    if (graph[front][i] == 1 && !visited[i]) {
                        queue.add(i);
                        visited[i] = true;
                    }
                }
            }
            System.out.println(verticesAtALevel);
            queue.poll();
            if(!queue.isEmpty()) queue.add(null);
        }
    }

}