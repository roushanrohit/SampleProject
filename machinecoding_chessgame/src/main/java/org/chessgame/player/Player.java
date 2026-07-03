package org.chessgame.player;

import org.chessgame.chessPiece.PieceColor;

public class Player {

    private String name;
    private PieceColor color;

    public Player(String name, PieceColor color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public PieceColor getColor() {
        return color;
    }
}
