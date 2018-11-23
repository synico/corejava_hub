package com.nick.ch4;

import java.io.IOException;
import java.net.Socket;
import java.nio.channels.Channel;

public class PlainOioClient {

    private static void connect2Server() {
        try {
            Socket socket = new Socket("localhost", 10080);
            Channel channel = socket.getChannel();
            System.out.println("Output: " + socket.getOutputStream().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for(;;) {
            PlainOioClient.connect2Server();
        }
    }

}
