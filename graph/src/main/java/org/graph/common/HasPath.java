package org.graph.common;

import java.util.LinkedList;
import java.util.Queue;

public class HasPath {

    public static void main(String[] args) {

        int[][] graph = new int[][]{{0,1,0,0,1},{1,0,0,0,0},{0,0,0,0,1},{0,0,0,0,1},{1,0,1,1,0}};
        boolean[] visited = new boolean[graph.length];
        int si = 0;
        int ei = 4;
        System.out.println("Path exists between " + si + " and " + ei + " : " + hasPathDFS(graph, si, ei, visited));
        System.out.println("Path exists between " + si + " and " + ei + " : " + hasPathBFS(graph, si, ei, visited));

    }

    private static boolean hasPathBFS(int[][] graph, int si, int ei, boolean[] visited) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(si);
        visited[si] = true;

        while(!queue.isEmpty()){
            int front = queue.poll();

            // front becomes same as ei
            if(front == ei){
                return true;
            }

            for(int i = 0; i < graph.length; i++){
                if(graph[front][i] == 1 && !visited[i]){
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }

        return false;
    }

    private static boolean hasPathDFS(int[][] graph, int si, int ei, boolean[] visited) {

        // si becomes same as ei
        if(si == ei) return true;
        visited[si] = true;

        for(int i = 0; i < graph.length; i++){
            if(graph[si][i] == 1 && !visited[i]){

                // if there exists a path from si unvisited neighbour, there exists a path from si
                if(hasPathDFS(graph, i, ei, visited)){
                    return true;
                }
            }
        }

        // adjacent bhi nhi tha aur kisi bhi neighbour se bhi path nhi aaya -- no path found
        return false;
    }
}
