package org.example.designpatterns.creational.prototype.snakeandladder;

import java.util.Random;
import java.util.Scanner;

public class SnakeAndLadderGame {

    public static void main(String[] args) {

        // snakes : mouth and tail
        int[][] snakes = {{99,1},{72,18},{23,2},{88,44}};
        // ladders : base and top
        int[][] ladders = {{19,91},{30,71},{43,77},{4,82}};
        // set max position
        int maxPosition = 100;
        // initialize the game board
        SnakeAndLadderBoard board = new SnakeAndLadderBoard(snakes, ladders, maxPosition);

        // initialize the pieces
        GamePiece redPiece = new GamePiece("red", 0);
        GamePiece bluePiece = new GamePiece("blue", 0);
        board.addPiece(redPiece);
        board.addPiece(bluePiece);
        // check board state
        // board.showBoardState();

        // start the game
        play(board);
    }

    public static void play(SnakeAndLadderBoard gameBoard){

        int chanceNo = 0;
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        while(!checkIfGameOver(gameBoard)) {
            for(GamePiece gamePiece : gameBoard.getPieces()){

                System.out.println(gamePiece.getColor() + " player, please roll the dice(choose a number between 1 and 100): ");
                int num = random.nextInt(sc.nextInt()) % 6 + 1;
                int currPos = gamePiece.getPosition();
                int newPos = currPos + num;
                chanceNo++;

                if(gameBoard.snakeMap.containsKey(newPos)){

                    // bitten by snake
                    System.out.println(gamePiece.getColor() + " bitten by a snake");
                    gamePiece.setPosition(gameBoard.snakeMap.get(newPos));
                } else if (gameBoard.ladderMap.containsKey(newPos)){

                    // caught a ladder
                    System.out.println(gamePiece.getColor() + " caught a ladder");
                    gamePiece.setPosition(gameBoard.ladderMap.get(newPos));
                } else if(newPos <= gameBoard.getMaxPosition()){

                    // move to the next position
                    gamePiece.setPosition(newPos);
                }

                System.out.println(gamePiece);
                if(gamePiece.getPosition() == gameBoard.getMaxPosition()){
                    System.out.println(gamePiece.getColor() + " is the winner after " + chanceNo + " chances.");
                    break;
                }
            }
        }
        sc.close();
    }

    private static boolean checkIfGameOver(SnakeAndLadderBoard board) {
        for(GamePiece piece : board.getPieces()){
            if(piece.getPosition() == board.getMaxPosition()){
                return true;
            }
        }
        return false;
    }
}
