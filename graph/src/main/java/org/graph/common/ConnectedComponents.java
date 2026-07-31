package org.graph.common;

public class ConnectedComponents {

    public static void main(String[] args) {
        int[][] graph = new int[][]{{0,1,0,0,1},{1,0,0,0,0},{0,0,0,0,1},{0,0,0,0,1},{1,0,1,1,0}};
        boolean[] visited = new boolean[graph.length];
        int k = 0;
        for(int i = 0; i < graph.length; i++){
            if(!visited[i]){
                k++;
                dfs(graph, visited, i);
            }
        }
        System.out.println("Number of connected components: " + k);
    }

    public static void dfs(int[][] graph, boolean[] visited, int sv){
        visited[sv] = true;
        for(int i = 0; i < graph.length; i++){
            if(graph[sv][i] == 1 && !visited[i]){
                dfs(graph, visited, i);
            }
        }
    }
}
