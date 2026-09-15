package org.chessgame.chessboard;

import org.chessgame.chessPiece.ChessPiece;

public class Cell {

    private Position position;
    private ChessPiece piece;

    public Cell(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public ChessPiece getPiece() {
        return piece;
    }

    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    public boolean isEmpty(){
        return piece == null;
    }
}
