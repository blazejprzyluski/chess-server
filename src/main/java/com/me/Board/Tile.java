package com.me.Board;

import com.me.pieces.Piece;


public class Tile {
    private int positionX;
    private int positionY;
    private boolean isOccupied;
    private Piece p;

    public Tile(int x, int y, Piece p) {
        this.p = p;
        positionX = x;
        positionY = y;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setPiece(Piece p) {
        if (p == null) {
            this.isOccupied = false;
            return;
        } else {
            this.isOccupied = true;
        }
        this.p = p;
        p.setY(this.positionY);
        p.setX(this.getPositionX());
    }

    public Piece getPiece() {
        return this.p;
    }


    public int getPositionX() {
        return positionX;
    }


    public int getPositionY() {
        return positionY;
    }

}
