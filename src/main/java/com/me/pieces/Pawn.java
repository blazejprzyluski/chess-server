package com.me.pieces;

import com.me.Board.GameBoard;

import java.util.ArrayList;

public class Pawn extends Piece
{
    public Pawn(int x, int y, Player player)
    {
        super(x, y, player);
    }

    @Override
    public ArrayList<Move> availableMoves(GameBoard gb)
    {
        ArrayList<Move> moves = new ArrayList<>();
        int x = this.getX();
        int y = this.getY();

        if(this.getPlayer() == Player.BLACK)
        {
            checkMovesBlack(x,y,gb,moves);
        }
        else
        {
            checkMovesWhite(x,y,gb,moves);
        }

        return moves;
    }

    private void checkMovesBlack(int x, int y, GameBoard gb, ArrayList<Move> m)
    {
        if(y == 1)
        {
            if(!gb.getTile(x, y + 1).isOccupied() && !gb.getTile(x, y + 2).isOccupied())
            {
                m.add(new Move(x,y + 1));
                m.add(new Move(x,y + 2));
            }
            else if(!gb.getTile(x, y + 1).isOccupied())
            {
                m.add(new Move(x, y + 1));
            }
        }
        else
        {
            if(y + 1 < 8 && !gb.getTile(x,y + 1).isOccupied())
            {
                m.add(new Move(x,y + 1));
            }
        }
        if(x + 1 < 8 && y + 1 < 8)
        {
            if(gb.getTile(x + 1,y + 1).isOccupied() && gb.getTile(x + 1, y + 1).getPiece().getPlayer() != this.getPlayer())
            {
                m.add(new Beat(x  + 1, y + 1));
            }
        }
        if(x - 1 > 0 && y + 1 < 8)
        {
            if(gb.getTile(x - 1, y + 1).isOccupied() && gb.getTile(x -1, y + 1).getPiece().getPlayer() != this.getPlayer())
            {
                m.add(new Beat(x - 1,y + 1));
            }
        }
    }

    private void checkMovesWhite(int x, int y, GameBoard gb, ArrayList<Move> m)
    {
        if(y == 6)
        {
            if(!gb.getTile(x, y - 2).isOccupied() && !gb.getTile(x, y - 1).isOccupied())
            {
                m.add(new Move(x,y - 1));
                m.add(new Move(x,y - 2));
            }
            else if(!gb.getTile(x, y - 1).isOccupied())
            {
                m.add(new Move(x, y - 1));
            }
        }
        else
        {
            if(y - 1 >= 0 && !gb.getTile(x,y - 1).isOccupied())
            {
                m.add(new Move(x,y - 1));
            }
        }
        if(x + 1 < 8 && y - 1 >= 0)
        {
            if(gb.getTile(x + 1,y - 1).isOccupied() && gb.getTile(x + 1, y - 1).getPiece().getPlayer() != this.getPlayer())
            {
                m.add(new Beat(x  + 1, y - 1));
            }
        }
        if(x - 1 > 0 && y - 1 >= 0)
        {
            if(gb.getTile(x - 1, y - 1).isOccupied() && gb.getTile(x - 1, y - 1).getPiece().getPlayer() != this.getPlayer())
            {
                m.add(new Beat(x - 1,y - 1));
            }
        }
    }

    @Override
    public String toString()
    {
        return this.getPlayer() + " PAWN";
    }
}
