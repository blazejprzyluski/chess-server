package com.me;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;

public class ChessPlayer extends Thread {
    private Socket socket;

    public ChessPlayer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (true) {
            }
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                // Oh, well!
            }
        }
    }

    void sendData(IncomingData data) {
        OutputStream oStream = null;
        try {
            oStream = this.socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert oStream != null;
        PrintWriter pWriter = new PrintWriter(oStream, true);
        pWriter.println(createJSON(data));
        pWriter.flush();
    }

    IncomingData getData() throws IOException {
        InputStream iStream = null;
        try {
            iStream = this.socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader receiveRead = new BufferedReader(new InputStreamReader(iStream));
        IncomingData data = readFromJSON(receiveRead.readLine());
        return data;
    }

    private IncomingData readFromJSON(String data) {
        if(data == null) {
            return new IncomingData(null, null);
        }
        ObjectMapper o = new ObjectMapper();
        try {
            return o.readValue(data, IncomingData.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String createJSON (IncomingData data) {
        ObjectMapper o = new ObjectMapper();
        try {
            return o.writeValueAsString(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

