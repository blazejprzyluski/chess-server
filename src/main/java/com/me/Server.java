package com.me;

import com.me.Board.GameBoard;

import java.io.IOException;

import static com.me.Board.Player.BLACK;
import static com.me.Board.Player.WHITE;

public class Server {
    private static GameBoard gb = new GameBoard();
    private static ChessPlayer player1;
    private static ChessPlayer player2;

    public static void main(String[] args) throws IOException {
        ServerImpl server = new ServerImpl();

        server.start();
        player1 = server.getPlayerOne();
        player2 = server.getPlayerTwo();
        IncomingData data;

        while (true) {
            if (gb.getActivePlayer() == WHITE) {
                data = player1.getData();
                player2.sendData(new IncomingData(BLACK, data.getMove()));
                gb.setActivePlayer();
            } else {
                data = player2.getData();
                player1.sendData(new IncomingData(WHITE, data.getMove()));
                gb.setActivePlayer();
            }
        }
    }
}
