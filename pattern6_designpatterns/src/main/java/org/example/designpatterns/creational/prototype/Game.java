package org.example.designpatterns.creational.prototype;

public class Game {

    public static void main(String[] args) {

        GameBoard board = new GameBoard();

        // initialize the pieces
        GamePiece redPiece = new GamePiece("red", 0);
        GamePiece bluePiece = new GamePiece("blue", 5);
        board.addPiece(redPiece);
        board.addPiece(bluePiece);

        // check board state
        board.showBoardState();

        // client is not worried about how the copy is happening - decoupling
        GameBoard newBoard = board.clone();

        newBoard.showBoardState();
    }
}
