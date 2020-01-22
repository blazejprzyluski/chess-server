package com.me;

import com.me.pieces.Player;

import java.io.IOException;
import java.net.ServerSocket;

import static com.me.pieces.Player.BLACK;
import static com.me.pieces.Player.WHITE;

public class ServerImpl {
    private final int PORT = 5000;
    private final ServerSocket serverSocket;
    private ChessPlayer playerOne;
    private ChessPlayer playerTwo;
    private ChessPlayer dummy;

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

        dummy = new ChessPlayer(serverSocket.accept());
        System.out.println("Helper for player has connected");
        dummy.start();

        playerOne.start();
        playerTwo.start();
    }

    public ChessPlayer getPlayerOne() {
        return playerOne;
    }


    public ChessPlayer getPlayerTwo() {
        return playerTwo;
    }

    public ChessPlayer getDummy() {
        return dummy;
    }
}
