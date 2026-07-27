package org.graph.common;

// A graph is a valid tree if it is all connected and does not have any cycles
public class GraphValidTree {

    public static void main(String[] args) {

        boolean isValidTree = false;
        int[][] graph = new int[][]{{0,1,0,0,1},{1,0,0,0,0},{0,0,0,0,1},{0,0,0,0,1},{1,0,1,1,0}};
        boolean[] visited = new boolean[graph.length];
        boolean cycleDetected = detectCycle(graph, visited, 0, -1);
        if(!cycleDetected) isValidTree = true;
        for (boolean b : visited) {
            if (!b) {
                isValidTree = false;
                break;
            }
        }
        System.out.println("Is valid tree: " + isValidTree);
    }

    /*
        If you visit a node that is already visited and is not your parent, cycle exists
        For Undirected graphs
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
