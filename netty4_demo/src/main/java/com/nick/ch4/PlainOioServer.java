package com.nick.ch4;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class PlainOioServer {

    public void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        try {
            for(;;) {
                //block thread here, until there is a client send connection request to server
                final Socket clientSocket = socket.accept();
                System.out.println("Accepted connection from " + clientSocket);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OutputStream out;
                        try {
                            out = clientSocket.getOutputStream();
                            out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
                            out.flush();
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                clientSocket.close();
                            } catch (IOException ex) {
                                // ignore on close
                            }
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PlainOioServer oioServer = new PlainOioServer();
        try {
            oioServer.serve(10080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
