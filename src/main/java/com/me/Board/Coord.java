package com.me.Board;

public class Coord {
    private int x;
    private int y;
    private int coords;

    public Coord(int coords) {
        this.x = coords % 8;
        this.y = coords / 8;
        this.coords = coords;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCoords() {
        return coords;
    }

    public void setCoords(int coords) {
        this.coords = coords;
    }
}
