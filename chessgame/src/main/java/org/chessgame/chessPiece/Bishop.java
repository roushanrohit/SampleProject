package org.chessgame.chessPiece;

import org.chessgame.chessboard.ChessBoard;
import org.chessgame.chessboard.Position;

public class Bishop extends ChessPiece {

    public Bishop(PieceColor color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color == PieceColor.WHITE ? "B" : "b";
    }

    @Override
    public boolean canMove(ChessBoard board, Position from, Position to) {
        return false;
    }
}
