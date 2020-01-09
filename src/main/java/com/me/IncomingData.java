package com.me;

import com.me.pieces.Player;

import java.util.Objects;

public class IncomingData {
    private Player player;
    private Move move;

    public IncomingData() {
    }

    public IncomingData(Player player, Move move) {
        this.player = player;
        this.move = move;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncomingData that = (IncomingData) o;
        return player == that.player &&
                Objects.equals(move, that.move);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, move);
    }

    @Override
    public String toString() {
        return "IncomingData{" +
                "player=" + player +
                ", move=" + move +
                '}';
    }
}
