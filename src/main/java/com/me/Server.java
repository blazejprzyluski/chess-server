package com.me;

import com.me.Board.GameBoard;
import com.me.pieces.Player;

import java.io.IOException;

import static com.me.pieces.Player.BLACK;
import static com.me.pieces.Player.WHITE;

public class Server {
    private static GameBoard gb = new GameBoard();
    private static ChessPlayer player1;
    private static ChessPlayer player2;

    public static void main(String[] args) throws IOException {
        ServerImpl server = new ServerImpl();

        server.start();
        player1 = server.getPlayerOne();
        player2 = server.getPlayerTwo();
        IncomingData data = null;
        IncomingData oldData = null;
        //TODO logika ruchu przy pomocy nowej klasy (łatwe), najpierw w formie tekstowej DONE
        //dodatkowo, wysyłanie i czytanie jsona na żądanie DONE
        //TODO dodanie kolejnego klienta który bedzie wysyłał ruchy, identyfikacja gracza DONE
        //json który będzie wysyłany, zawiera w sobie inofrmacje o graczu, i ruch DONE
        //osobny projekt na klienta, osobny na projekt, klient tylko wysyła ruchy, serwer reszte DONE
        //Aplikacja kliencka multithread, wiesza się na getData, zobaczyć o co chodzi, dobry progres, kolej graczy ustalone DONE
        //zachowanie w momencie kiedy nie twoja tura DONE
        // w ui dać wysyłanie z aplikacji klienckiej,


        while (true) {
            if (gb.getActivePlayer() == WHITE) {
                data = player1.getData();
                player2.sendData(new IncomingData(BLACK, data.getMove()));
                System.out.println("WHITE player move: " + data);
                gb.setActivePlayer();
            } else {
                data = player2.getData();
                player1.sendData(new IncomingData(WHITE, data.getMove()));
                System.out.println("Move from BLACK player: " + data);
                gb.setActivePlayer();
            }

        }
    }
}
