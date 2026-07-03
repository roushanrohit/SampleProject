package org.chessgame.util;

import org.chessgame.chessboard.Position;

public class MovementUtil {

    public static boolean isStraight(Position from, Position to){
        return (from.getRow() == to.getRow()) || (from.getColumn() == to.getColumn());
    }

    public static boolean isDiagonal(Position from, Position to){
        return Math.abs(from.getRow() - to.getRow()) == Math.abs(from.getColumn() - to.getColumn());
    }
}
