package org.chessgame.chessPiece;

import org.chessgame.chessboard.ChessBoard;
import org.chessgame.chessboard.Position;
import org.chessgame.util.MovementUtil;

public class Queen extends ChessPiece {

    public Queen(PieceColor color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color == PieceColor.WHITE ? "Q" : "q";
    }

    @Override
    public boolean canMove(ChessBoard board, Position from, Position to) {
        return MovementUtil.isStraight(from, to) || MovementUtil.isDiagonal(from, to);
    }
}
