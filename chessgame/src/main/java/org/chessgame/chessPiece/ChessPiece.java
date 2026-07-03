package org.chessgame.chessPiece;

import org.chessgame.chessboard.ChessBoard;
import org.chessgame.chessboard.Position;

public abstract class ChessPiece {

    PieceColor color;
    boolean captured;

    public ChessPiece(PieceColor color) {
        this.color = color;
    }

    public PieceColor getColor() {
        return color;
    }

    public boolean isCaptured() {
        return captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public abstract String getSymbol();

    public abstract boolean canMove(ChessBoard board, Position from, Position to);
}
