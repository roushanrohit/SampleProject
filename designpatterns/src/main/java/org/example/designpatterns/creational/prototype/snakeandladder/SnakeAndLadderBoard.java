package org.example.designpatterns.creational.prototype.snakeandladder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnakeAndLadderBoard {

    List<GamePiece> pieces;
    Map<Integer, Integer> snakeMap;
    Map<Integer, Integer> ladderMap;
    int maxPosition = 100;

    public SnakeAndLadderBoard(int[][] snakes, int[][] ladders, int maxPosition){
        this.pieces = new ArrayList<>();
        this.snakeMap = getMap(snakes);
        this.ladderMap = getMap(ladders);
        this.maxPosition = maxPosition;
    }

    public void addPiece(GamePiece piece){
        pieces.add(piece);
    }

    public List<GamePiece> getPieces(){
        return pieces;
    }

    public int getMaxPosition() {
        return maxPosition;
    }

    public void showBoardState(){
        for(GamePiece piece : pieces){
            System.out.println(piece);
        }
    }

    private static Map<Integer, Integer> getMap(int[][] arr) {
        Map<Integer, Integer> hmap = new HashMap<>();
        for(int[] a : arr){
            hmap.put(a[0],a[1]);
        }
        return hmap;
    }
}
