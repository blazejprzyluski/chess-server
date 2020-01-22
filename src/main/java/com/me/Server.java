package com.me;

import com.me.Board.GameBoard;

import java.io.IOException;

import static com.me.pieces.Player.BLACK;
import static com.me.pieces.Player.WHITE;

public class Server {
    private static GameBoard gb = new GameBoard();
    private static ChessPlayer player1;
    private static ChessPlayer player2;
    private static ChessPlayer dummy;

    public static void main(String[] args) throws IOException {
        ServerImpl server = new ServerImpl();

        server.start();
        player1 = server.getPlayerOne();
        player2 = server.getPlayerTwo();
        dummy = server.getDummy();
        IncomingData data;

        while (true) {
            if (gb.getActivePlayer() == WHITE) {
                data = player1.getData();
                dummy.sendData(data);
                player2.sendData(new IncomingData(BLACK, data.getMove()));
                System.out.println("WHITE player move: " + data);
                gb.setActivePlayer();
            } else {
                data = player2.getData();
                dummy.sendData(data);
                player1.sendData(new IncomingData(WHITE, data.getMove()));
                System.out.println("Move from BLACK player: " + data);
                gb.setActivePlayer();
            }
        }
    }
}
