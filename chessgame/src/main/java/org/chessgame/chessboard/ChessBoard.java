package org.chessgame.chessboard;

import org.chessgame.chessPiece.*;

public class ChessBoard {

    private Cell[][] cells;
    private King whiteKing;
    private King blackKing;

    public ChessBoard() {
        this.cells = new Cell[8][8];
        initializeBoard();
    }

    private void initializeBoard() {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                cells[i][j] = new Cell(new Position(i, j));
            }
        }

        placeWhitePieces();
        placeBlackPieces();
    }

    private void placeBlackPieces() {
        // pawns
        for(int col = 0; col < 8; col++){
            cells[1][col].setPiece(new Pawn(PieceColor.BLACK));
        }
        // Rooks
        cells[0][0].setPiece(new Rook(PieceColor.BLACK));
        cells[0][7].setPiece(new Rook(PieceColor.BLACK));

        // Knights
        cells[0][1].setPiece(new Knight(PieceColor.BLACK));
        cells[0][6].setPiece(new Knight(PieceColor.BLACK));

        // Bishops
        cells[0][2].setPiece(new Bishop(PieceColor.BLACK));
        cells[0][5].setPiece(new Bishop(PieceColor.BLACK));

        // Queen
        cells[0][3].setPiece(new Queen(PieceColor.BLACK));

        // King
        blackKing = new King(PieceColor.BLACK);
        cells[0][4].setPiece(blackKing);
    }

    private void placeWhitePieces() {
        // pawns
        for(int col = 0; col < 8; col++){
            cells[6][col].setPiece(new Pawn(PieceColor.WHITE));
        }
        // Rooks
        cells[7][0].setPiece(new Rook(PieceColor.WHITE));
        cells[7][7].setPiece(new Rook(PieceColor.WHITE));

        // Knights
        cells[7][1].setPiece(new Knight(PieceColor.WHITE));
        cells[7][6].setPiece(new Knight(PieceColor.WHITE));

        // Bishops
        cells[7][2].setPiece(new Bishop(PieceColor.WHITE));
        cells[7][5].setPiece(new Bishop(PieceColor.WHITE));

        // Queen
        cells[7][3].setPiece(new Queen(PieceColor.WHITE));

        // King
        whiteKing = new King(PieceColor.WHITE);
        cells[7][4].setPiece(whiteKing);
    }

    public Cell getCell(Position position){
        return cells[position.getRow()][position.getColumn()];
    }

    public void movePiece(Position from, Position to){
        Cell source = getCell(from);
        Cell destination = getCell(to);

        if (!destination.isEmpty()) {
            destination.getPiece().setCaptured(true);
        }

        destination.setPiece(source.getPiece());
        source.setPiece(null);
    }

    public boolean isKingCaptured(PieceColor color) {

        if (color == PieceColor.WHITE) {
            return whiteKing.isCaptured();
        }
        return blackKing.isCaptured();
    }

    public void printBoard() {

        System.out.println();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                ChessPiece piece = cells[i][j].getPiece();
                if(piece == null){
                    System.out.print(" . ");
                }else {
                    System.out.print(piece.getSymbol() + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isKingInCheck(PieceColor kingColor) {

        Position kingPosition = getPosition(kingColor == PieceColor.WHITE ? whiteKing : blackKing);
        PieceColor attackerColor = kingColor == PieceColor.WHITE ? PieceColor.BLACK : PieceColor.WHITE;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                ChessPiece piece = cells[row][col].getPiece();
                if (piece == null) continue;
                if (piece.getColor() != attackerColor) continue;
                Position from = new Position(row, col);
                if (piece.canMove(this, from, kingPosition)) {
                    return true;
                }
            }
        }

        return false;
    }

    public Position getPosition(ChessPiece piece) {

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                if (cells[row][col].getPiece() == piece) {
                    return cells[row][col].getPosition();
                }
            }
        }

        return null;
    }
}
