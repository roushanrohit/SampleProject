package org.chessgame;

import org.chessgame.chessPiece.PieceColor;
import org.chessgame.chessboard.Position;
import org.chessgame.game.ChessGame;
import org.chessgame.player.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Player white = new Player("Rohit", PieceColor.WHITE);
        Player black = new Player("Arun", PieceColor.BLACK);

        ChessGame chessGame = new ChessGame(white, black);
        Scanner scanner = new Scanner(System.in);

        while (true) {

            chessGame.printBoard();

            System.out.println();
            System.out.println(chessGame.getCurrentPlayer().getName() + "'s Turn");
            System.out.println("Enter move: fromRow fromCol toRow toCol");
            System.out.println("Example: 6 4 4 4");
            System.out.println("Type 'exit' to quit");

            String input = scanner.nextLine();

            if ("exit".equalsIgnoreCase(input)) break;

            try {

                String[] tokens = input.trim().split("\\s+");
                if (tokens.length != 4) {
                    System.out.println("Invalid input.");
                    continue;
                }
                Position from = new Position(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));

                Position to = new Position(Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));

                chessGame.move(from, to);

            } catch (Exception e) {
                System.out.println("Move failed: " + e.getMessage());
            }
        }

        scanner.close();
    }
}