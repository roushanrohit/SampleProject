package org.example.designpatterns.creational.prototype.snakeandladder;

public class GamePiece {

    private String color;
    private int position;

    public GamePiece(String color, int position) {
        this.color = color;
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "GamePiece{" +
                "color='" + color + '\'' +
                ", position=" + position +
                '}';
    }
}
