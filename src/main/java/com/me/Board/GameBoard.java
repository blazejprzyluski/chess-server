package com.me.Board;

import com.me.Move;
import com.me.pieces.*;

import static com.me.pieces.Player.*;

public class GameBoard {
    //array of tiles
    private Tile[] tiles;

    //setting turn
    private int turn;

    //variables that determine whether white or black king is beaten
    private boolean whiteKing;
    private boolean blackKing;

    private Player activePlayer;

    public GameBoard() {
        this.turn = 1;
        this.tiles = new Tile[64];
        //populating tiles array with empty tiles
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[xyTo1D(i, j)] = new Tile(i, j, null);
            }
        }

        //populating tiles with black and white figures
        populateBlack();
        populateWhite();

        //initially both kings are not beaten
        blackKing = true;
        whiteKing = true;

        activePlayer = WHITE;
    }

    public void setWhiteKing(boolean whiteKing) {
        this.whiteKing = whiteKing;
    }

    public boolean isBlackKing() {
        return blackKing;
    }

    public void setBlackKing(boolean blackKing) {
        this.blackKing = blackKing;
    }

    private void populateBlack() {
        tiles[0].setPiece(new Rook(0, 0, BLACK));

        tiles[1].setPiece(new Knight(1, 0, BLACK));

        tiles[2].setPiece(new Bishop(2, 0, BLACK));

        tiles[3].setPiece(new King(3, 0, BLACK));

        tiles[4].setPiece(new Queen(4, 0, BLACK));

        tiles[5].setPiece(new Bishop(5, 0, BLACK));

        tiles[6].setPiece(new Knight(6, 0, BLACK));

        tiles[7].setPiece(new Rook(7, 0, BLACK));


        int x = 0;
        for (int i = 8; i <= 15; i++) {
            tiles[i].setPiece(new Pawn(x, 1, BLACK));

            x++;
        }
    }

    private void populateWhite() {
        tiles[56].setPiece(new Rook(0, 7, WHITE));

        tiles[57].setPiece(new Knight(1, 7, WHITE));

        tiles[58].setPiece(new Bishop(2, 7, WHITE));

        tiles[59].setPiece(new King(3, 7, WHITE));

        tiles[60].setPiece(new Queen(4, 7, WHITE));

        tiles[61].setPiece(new Bishop(5, 7, WHITE));

        tiles[62].setPiece(new Knight(6, 7, WHITE));

        tiles[63].setPiece(new Rook(7, 7, WHITE));


        int x = 0;
        for (int i = 48; i <= 55; i++) {
            tiles[i].setPiece(new Pawn(x, 7, WHITE));
            x++;
        }
    }

    //changing x and y to 1D coordinate
    private int xyTo1D(int x, int y) {
        return y * 8 + x;
    }


    public Tile[] getTiles() {
        return this.tiles;
    }

    public Tile getTile(Integer coords) {
        Coord c = new Coord(coords);
        return getTile(c.getX(), c.getY());
    }

    public Tile getTile(int x, int y) {
        if (x >= 0 && x < 8 && y >= 0 && y < 8) {
            return tiles[xyTo1D(x, y)];
        }
        return null;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void makeMove(Move m) {
        Tile tileFrom = tiles[m.getFrom()];
        Tile tileTo = tiles[m.getTo()];
        Piece pieceFrom = tileFrom.getPiece();
        Piece pieceTo;


        if (tileTo.isOccupied()) {
            pieceTo = tileTo.getPiece();
            if (pieceFrom.getPlayer() != pieceTo.getPlayer()) {
                beat(tileFrom, tileTo);
                setTurn();
            }
        } else {
            tileTo.setPiece(pieceFrom);
            tileFrom.setPiece(null);
            setTurn();
        }
    }

    private void beat(Tile t1, Tile t2) {
        Piece p = t1.getPiece();
        t1.setPiece(null);
        t2.setPiece(p);
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer() {
        if (this.activePlayer == WHITE) {
            this.activePlayer = BLACK;
        } else {
            this.activePlayer = WHITE;
        }
    }

    private void setTurn() {
        this.turn += 1;
    }

    public void waitForTurn(int turn) throws InterruptedException {
        if (turn != this.turn) {
            Thread.sleep(3000);
        }
    }
}
