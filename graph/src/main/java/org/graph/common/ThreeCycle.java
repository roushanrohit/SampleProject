package org.graph.common;

import java.util.*;

public class ThreeCycle {

    public static void main(String[] args) {

        int[][] graph = new int[][]{{0,1,0,0,1},{1,0,0,0,0},{0,0,0,0,1},{0,0,0,0,1},{1,0,1,1,0}};
        System.out.println("Three cycles exist in the graph: " + threeCycle(graph));
    }

    // Brute force solution -- Time Complexity - O(n3)
    public static boolean threeCycle(int[][] graph){

        int n = graph.length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){

                    if(graph[i][j] == 1 && graph[j][k] == 1 && graph[k][i] == 1){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int threeCycleOptimized( List<Set<Integer>> graph){

        int triangleCount = 0;
        for (Set<Integer> neighbours : graph) {

            // Convert the neighbor set to a list so it can be indexed,
            List<Integer> neighboursList = new ArrayList<>(neighbours);
            for (int i = 0; i < neighbours.size(); i++) {
                for (int j = i + 1; j < neighbours.size(); j++) {
                    // vertex at ith position and vertex at jth position are neighbours of the same vertex,
                    // if they themselves are neighbours, three cycle/triangle exists
                    int v = neighboursList.get(i);
                    int w = neighboursList.get(j);
                    if (graph.get(v).contains(w)) {
                        triangleCount++;
                    }

                }
            }
        }

        return triangleCount/3;
    }
}
