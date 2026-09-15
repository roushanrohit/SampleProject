package org.graph.common;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class KnightMoves {

    static int[][] dir = {{2,1},{2,-1},{-2,-1},{-2,1},{1,2},{1,-2},{-1,-2},{-1,2}};
    public static void main(String[] args) {

        int n = 8;
        int startx = 0, starty = 0;
        int targetx = 7, targety = 7;
        System.out.println("Minimum moves needed to reach " + targetx + "," + targety
                + " from " + startx + "," + starty + " is: " + knightmoves(n, startx, starty, targetx, targety));
    }

    public static int knightmoves(int n, int startx, int starty, int targetx, int targety){

        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startx, starty, 0});
        visited[startx][starty] = true;

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int moves = curr[2];
            if(x == targetx && y == targety) return moves;

            for(int[] d : dir){
                int newx = x + d[0];
                int newy = y + d[1];
                if(newx >= 0 && newx < n && newy >= 0 && newy < n && !visited[newx][newy]){
                    visited[newx][newy] = true;
                    queue.add(new int[]{newx, newy, moves + 1});
                }
            }
        }

        return -1;
    }

    /*
        In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
        Return the minimum number of steps needed to move the knight to the square [x, y].
        It is guaranteed the answer exists.
     */
    public static int knightmovesunbounded(int targetx, int targety){

        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0});
        visited.add("0_0");

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int moves = curr[2];
            if(x == targetx && y == targety) return moves;

            for(int[] d : dir){
                int newx = x + d[0];
                int newy = y + d[1];
                if(!visited.contains(newx+"_"+newy)){
                    visited.add(newx+"_"+newy);
                    queue.add(new int[]{newx, newy, moves + 1});
                }
            }
        }

        return -1;
    }
}
