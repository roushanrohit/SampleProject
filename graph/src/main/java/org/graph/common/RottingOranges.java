package org.graph.common;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {

    public static void main(String[] args) {

        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        int rows = grid.length;
        int columns = grid[0].length;

        Queue<Pair> queue = new LinkedList<>();
        int fresh = 0;

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(grid[i][j] == 2){
                    queue.add(new Pair(i, j));
                } else if(grid[i][j] == 1){
                    fresh++;
                }
            }
        }

        if(fresh == 0) {
            System.out.println(0);
        } else {

            int minutes = 0;
            int[][] directions = {{1,0},{-1, 0},{0,1},{0, -1}};

            // BFS
            while(!queue.isEmpty()){
                int size = queue.size();
                boolean rottenThisRound = false;

                for(int i=0; i < size; i++){
                    Pair p = queue.poll();
                    for(int[] dir : directions){
                        int newRow = p.first + dir[0];
                        int newCol = p.second + dir[1];

                        if(newRow >= 0 && newRow < rows && newCol >= 0 && newCol < columns){
                            if(grid[newRow][newCol] == 1){
                                grid[newRow][newCol] = 2;
                                fresh--;
                                rottenThisRound = true;
                                queue.add(new Pair(newRow, newCol));
                            }
                        }

                    }
                }

                if(rottenThisRound) minutes++;
            }

            System.out.println(fresh == 0 ? minutes : -1);
        }
    }
}

class Pair {
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
