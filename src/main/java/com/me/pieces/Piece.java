package com.me.pieces;


import com.me.Board.GameBoard;

import java.util.ArrayList;

public abstract class Piece
{
    private int x;
    private int y;
    private Player player;

    public Piece(int x, int y, Player player)
    {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public abstract ArrayList<Move> availableMoves(GameBoard gb);

    protected void beatCheck(int x, int y,ArrayList<Move> m, GameBoard gb)
    {
        if(x >=0 && x < 8 && y >= 0 && y < 8) {
            if (gb.getTile(x, y).isOccupied() && gb.getTile(x, y).getPiece().getPlayer() != this.getPlayer()) {
                m.add(new Beat(x, y));
            }
        }
    }

    protected void checkDiagonally(int x, int y, GameBoard gb, ArrayList<Move> moves)
    {
        checker(x + 1, y + 1, gb, moves, 1 , 1);
        checker(x + 1, y - 1, gb, moves, 1 , -1);
        checker(x - 1, y + 1, gb, moves, -1 , 1);
        checker(x - 1, y - 1, gb, moves, -1 , -1);
    }

    protected void checkVerticallyHorizontally(int x, int y, GameBoard gb, ArrayList<Move> moves)
    {
        checker(x + 1, y, gb, moves, 1 , 0);
        checker(x - 1, y, gb, moves, -1 , 0);
        checker(x, y + 1, gb, moves, 0 , 1);
        checker(x, y - 1, gb, moves, 0 , -1);
    }

    //checks iteratively whether tiles on the way are occupied and adds moves accordingly
    private void checker(int x, int y, GameBoard gb, ArrayList<Move> moves, int addX, int addY)
    {
        while(true)
        {
            if(x > 7 || y > 7 || x < 0 || y < 0)
                return;
            if(gb.getTile(x,y).isOccupied() && gb.getTile(x,y).getPiece().getPlayer() == this.getPlayer())
            {
                return;
            }
            else if(gb.getTile(x,y).isOccupied() && gb.getTile(x,y).getPiece().getPlayer() != this.getPlayer())
            {
                moves.add(new Beat(x,y));
                return;
            }
            moves.add(new Move(x,y));
            x += addX;
            y += addY;
        }
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    @Override
    public String toString()
    {
        return this.getPlayer() + " " + this.getClass().toString();
    }
}
