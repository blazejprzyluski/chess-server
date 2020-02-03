package com.me.Board;

import static com.me.Board.Player.BLACK;
import static com.me.Board.Player.WHITE;

public class GameBoard {

    private Player activePlayer;

    public GameBoard() {

        activePlayer = WHITE;
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

}
