package org.chessgame.chessPiece;

import org.chessgame.chessboard.ChessBoard;
import org.chessgame.chessboard.Position;

public class Knight extends ChessPiece {

    public Knight(PieceColor color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color == PieceColor.WHITE ? "KN" : "kn";
    }

    @Override
    public boolean canMove(ChessBoard board, Position from, Position to) {
        return false;
    }
}
