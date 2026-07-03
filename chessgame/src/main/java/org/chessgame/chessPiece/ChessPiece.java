package org.chessgame.chessPiece;

import org.chessgame.chessboard.ChessBoard;
import org.chessgame.chessboard.Position;

public abstract class ChessPiece {

    PieceColor color;

    public ChessPiece(PieceColor color) {
        this.color = color;
    }

    public PieceColor getColor() {
        return color;
    }

    public abstract String getSymbol();

    public abstract boolean canMove(ChessBoard board, Position from, Position to);
}
