package com.me.pieces;

import com.me.Board.GameBoard;

import java.util.ArrayList;

public class Rook extends Piece
{
    public Rook(int x, int y, Player player)
    {
        super(x, y, player);
    }

    @Override
    public ArrayList<Move> availableMoves(GameBoard gb)
    {
        ArrayList<Move> moves = new ArrayList<>();
        int x = this.getX();
        int y = this.getY();

        checkVerticallyHorizontally(x,y,gb,moves);

        return moves;
    }

    @Override
    public String toString()
    {
        return this.getPlayer() + " ROOK";
    }
}
