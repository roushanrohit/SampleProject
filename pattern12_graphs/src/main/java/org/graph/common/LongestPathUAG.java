package org.graph.common;

public class LongestPathUAG {

    public static void main(String[] args) {

        int[][] graph = new int[][]{{0,1,0,0,1},{1,0,0,0,0},{0,0,0,0,1},{0,0,0,0,1},{1,0,1,1,0}};
        System.out.println("Longest path in the undirected acyclic graph: " + longestPath(graph));
    }

    private static int longestPath(int[][] graph) {

        int longestPath = -1;
        for(int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph.length; j++){
                boolean[] visited = new boolean[graph.length];
                int path = pathLengthDFS(graph, i, j, visited);
                if(path > longestPath){
                    longestPath = path;
                }
            }
        }
        return longestPath;
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
