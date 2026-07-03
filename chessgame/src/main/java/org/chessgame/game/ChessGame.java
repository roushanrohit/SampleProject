package org.chessgame.game;

import org.chessgame.chessPiece.ChessPiece;
import org.chessgame.chessboard.Cell;
import org.chessgame.chessboard.ChessBoard;
import org.chessgame.chessboard.Position;
import org.chessgame.player.Player;

public class ChessGame {

    private ChessBoard board = new ChessBoard();
    private Player white;
    private Player black;
    private Player currentPlayer;

    public ChessGame(Player white, Player black) {
        this.white = white;
        this.black = black;

        board = new ChessBoard();
        currentPlayer = white;
    }

    public void move(Position from, Position to){

        Cell source = board.getCell(from);
        ChessPiece piece = source.getPiece();

        if(piece == null){
            throw new IllegalArgumentException();
        }

        if(piece.getColor() != currentPlayer.getColor()){
            throw new IllegalArgumentException();
        }

        if(!piece.canMove(board, from, to)){
            throw new IllegalArgumentException();
        }

        board.movePiece(from, to);
        switchTurn();
    }

    private void switchTurn() {
        currentPlayer = currentPlayer == white ? black : white;
    }

    public void printBoard(){
        board.printBoard();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
