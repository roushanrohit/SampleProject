package org.graph.common;

public class CycleDetection {

    public static void main(String[] args) {

        int[][] graph = new int[][]{{0,1,0,0,1},{1,0,0,0,0},{0,0,0,0,1},{0,0,0,0,1},{1,0,1,1,0}};
        boolean[] visited = new boolean[graph.length];
        boolean cycleExists = false;
        for(int i = 0; i < graph.length; i++){
            if(!visited[i]){
                if(detectCycle(graph, visited, i, -1)){
                    cycleExists = true;
                    break;
                }
            }
        }
        System.out.println(cycleExists);
    }

    /*
        If you visit a node that is already visited and is not your parent, cycle exists
     */
    private static boolean detectCycle(int[][] graph, boolean[] visited, int si, int parent) {

        visited[si] = true;
        for(int i = 0; i < graph.length; i++){
            if(graph[si][i] == 1){
                if(!visited[i]){
                    if(detectCycle(graph, visited, i, si)) return true;
                } else {
                    if(i != parent) return true;
                }
            }
        }
        return false;
    }
}
