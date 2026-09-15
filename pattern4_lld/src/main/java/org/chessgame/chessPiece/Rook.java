package org.chessgame.chessPiece;

import org.chessgame.chessboard.ChessBoard;
import org.chessgame.chessboard.Position;

public class Rook extends ChessPiece {

    public Rook(PieceColor color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color == PieceColor.WHITE ? "R" : "r";
    }

    @Override
    public boolean canMove(ChessBoard board, Position from, Position to) {
        return false;
    }
}
