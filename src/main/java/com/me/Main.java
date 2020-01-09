package com.me;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception
    {
        Socket sock = new Socket("127.0.0.1", 3000);
        BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
        OutputStream oStream = sock.getOutputStream();
        PrintWriter pWriter = new PrintWriter(oStream, true);

        // receiving from server ( receiveRead  object)
        InputStream iStream = sock.getInputStream();
        BufferedReader receiveRead = new BufferedReader(new InputStreamReader(iStream));

        System.out.println("Start the chitchat, type and press Enter key");
        Integer from;
        Integer to;
        while(true)
        {
            from = Integer.parseInt(keyReader.readLine());
            to = Integer.parseInt(keyReader.readLine());
            if(from == 69) {
                break;
            }
            pWriter.println(createJson(from, to));
            pWriter.flush();
        }
    }

    private static String createJson(int from, int to) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        if (mapper.canSerialize(Move.class)) {
            Move m = new Move(from,to);
            return mapper.writeValueAsString(m);
        } else {
            return null;
        }
    }
}
