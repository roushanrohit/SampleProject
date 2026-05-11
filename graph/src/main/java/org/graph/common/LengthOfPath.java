package org.graph.common;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LengthOfPath {

    public static void main(String[] args) {

        int[][] graph = new int[][]{{0,1,0,0,1},{1,0,0,0,0},{0,0,0,0,1},{0,0,0,0,1},{1,0,1,1,0}};
        int si = 0;
        int ei = 3;
        System.out.println("DFS Path Length: " + pathLengthDFS(graph, si, ei, new boolean[graph.length]));
        System.out.println("BFS Path Length: " + pathLengthBFS(graph, si, ei, new boolean[graph.length]));
    }

    private static int pathLengthBFS(int[][] graph, int si, int ei, boolean[] visited) {

        int[] dist = new int[graph.length];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(si);
        dist[si] = 0;
        visited[si] = true;

        while(!queue.isEmpty()) {
            int front = queue.poll();
            if(front == ei) {
                System.out.println("Distance Array:");
                for(int num : dist){
                    System.out.print(num + " ");
                }
                System.out.println();
                return dist[front];
            }

            for(int i = 0; i < graph.length; i++){
                if(graph[front][i] == 1 && !visited[i]){
                    queue.add(i);
                    dist[i] = dist[front] + 1;
                    visited[i] = true;
                }
            }
        }

        System.out.println("Distance Array:");
        for(int num : dist){
            System.out.print(num + " ");
        }
        System.out.println();

        return -1; // no path exists
    }

    private static int pathLengthDFS(int[][] graph, int si, int ei, boolean[] visited) {

        // si becomes same as ei
        if(si == ei) return 0;
        visited[si] = true;
        for(int i = 0; i < graph.length; i++){
            if(graph[si][i] == 1 && !visited[i]){
                int pathLength = pathLengthDFS(graph, i, ei, visited);
                if(pathLength != -1){
                    return pathLength + 1;
                }
            }
        }
        return -1; // no path found
    }
}
