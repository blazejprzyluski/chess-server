package com.me;

import java.io.IOException;
import java.net.ServerSocket;

import static com.me.Board.Player.BLACK;
import static com.me.Board.Player.WHITE;

public class ServerImpl {
    private final int PORT = 5000;
    private final ServerSocket serverSocket;
    private ChessPlayer playerOne;
    private ChessPlayer playerTwo;

    public ServerImpl() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }

    public void start() throws IOException {
        System.out.println("Waiting for connections.");
        playerOne = new ChessPlayer(serverSocket.accept());
        System.out.println("Player one connected.");
        playerOne.sendData(new IncomingData(WHITE, null));
        playerTwo = new ChessPlayer(serverSocket.accept());
        System.out.println("Player two connected.");
        playerTwo.sendData(new IncomingData(BLACK, null));

        playerOne.start();
        playerTwo.start();
    }

    public ChessPlayer getPlayerOne() {
        return playerOne;
    }


    public ChessPlayer getPlayerTwo() {
        return playerTwo;
    }
}
