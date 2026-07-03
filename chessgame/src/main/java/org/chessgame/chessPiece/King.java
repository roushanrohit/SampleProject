package org.chessgame.chessPiece;

import org.chessgame.chessboard.ChessBoard;
import org.chessgame.chessboard.Position;

public class King extends ChessPiece {

    public King(PieceColor color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color == PieceColor.WHITE ? "K" : "k";
    }

    @Override
    public boolean canMove(ChessBoard board, Position from, Position to) {

        int dr = Math.abs(from.getRow() - to.getRow());
        int dc = Math.abs(from.getColumn() - to.getColumn());
        return dr <= 1 && dc <= 1;
    }
}
