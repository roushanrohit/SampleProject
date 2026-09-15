package org.example.designpatterns.creational.prototype;

import java.util.ArrayList;
import java.util.List;

/*
    Each class should be responsible for creating its own copy
 */
public class GameBoard implements Prototype<GameBoard> {

    List<GamePiece> pieces;

    public GameBoard(){
        this.pieces = new ArrayList<>();
    }

    public void addPiece(GamePiece piece){
        pieces.add(piece);
    }

    public List<GamePiece> getPieces(){
        return pieces;
    }

    public void showBoardState(){
        for(GamePiece piece : pieces){
            System.out.println(piece);
        }
    }

    @Override
    public GameBoard clone() {

        /*
            Returns a deep copy since we are cloning the
            nested objects as well
         */
        GameBoard newBoard = new GameBoard();
        for(GamePiece piece : pieces){
            newBoard.addPiece(piece.clone());
        }
        return newBoard;
    }
}
