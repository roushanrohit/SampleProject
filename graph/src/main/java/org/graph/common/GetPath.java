package org.graph.common;

import java.util.*;

public class GetPath {

    public static void main(String[] args) {

        int[][] graph = new int[][]{{0,1,0,0,1},{1,0,0,0,0},{0,0,0,0,1},{0,0,0,0,1},{1,0,1,1,0}};
        boolean[] visited = new boolean[graph.length];
        int si = 0;
        int ei = 4;
        System.out.println("----DFS----");
        List<Integer> pathBetweenSiAndEi = getPathDFS(graph, si, ei, visited);
        for(Integer num : pathBetweenSiAndEi){
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("----BFS----");
        pathBetweenSiAndEi = getPathBFS(graph, si, ei, visited);
        for(Integer num : pathBetweenSiAndEi){
            System.out.print(num + " ");
        }
    }

    private static List<Integer> getPathBFS(int[][] graph, int si, int ei, boolean[] visited) {

        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> hmap = new HashMap<>();
        queue.add(si);
        hmap.put(si, -1);

        while(!queue.isEmpty()){
            int front = queue.poll();
            if(front == ei) break;

            for(int i = 0; i < graph.length; i++){
                if(graph[front][i] == 1 && !visited[i]){
                    queue.add(i);
                    hmap.put(i, front);
                    visited[i] = true;
                }
            }
        }

        if(hmap.get(ei) == null) return null;
        List<Integer> ans = new ArrayList<>();
        ans.add(ei);
        while (hmap.get(ei) != -1){
            ans.add(hmap.get(ei));
            ei = hmap.get(ei);
        }
        return ans;
    }

    private static List<Integer> getPathDFS(int[][] graph, int si, int ei, boolean[] visited) {

        // si becomes same as ei
        if(si == ei){
            visited[ei] = true;
            List<Integer> path = new ArrayList<>();
            path.add(ei);
            return path;
        }

        visited[si] = true;

        for(int i = 0; i < graph.length; i++){
            if(graph[si][i] == 1 && !visited[i]){
                List<Integer> path = getPathDFS(graph, i, ei, visited);
                if(path != null){
                    path.add(si);
                    return path;
                }
            }
        }
        return null;
    }
}
