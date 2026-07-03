package org.chessgame.chessPiece;

import org.chessgame.chessboard.ChessBoard;
import org.chessgame.chessboard.Position;

public class Pawn extends ChessPiece {

    public Pawn(PieceColor color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color == PieceColor.WHITE ? "P" : "p";
    }

    @Override
    public boolean canMove(ChessBoard board, Position from, Position to) {
        return false;
    }
}
