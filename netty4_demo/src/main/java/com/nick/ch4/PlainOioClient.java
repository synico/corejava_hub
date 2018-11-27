package com.nick.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.channels.Channel;

public class PlainOioClient {

    private static void connect2Server() {
        try {
            Socket socket = new Socket("localhost", 10080);
            Channel channel = socket.getChannel();
            // Receive stream/response from server
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String response = null;
            while ((response = br.readLine()) != null) {
                System.out.println("Response: " + response);
            }
            try {
                br.close();
                is.close();
                socket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /*for(;;)*/ {
            PlainOioClient.connect2Server();
        }
    }

}
