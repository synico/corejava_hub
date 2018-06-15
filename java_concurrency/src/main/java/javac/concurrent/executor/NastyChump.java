package javac.concurrent.executor;

import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class NastyChump {

    public static void main(String[] args) throws InterruptedException {
        Socket[] sockets = new Socket[400];
        for (int i = 0; i < sockets.length; i++) {
            try {
                sockets[i] = new Socket("localhost", 8080);
                System.out.println(i);
                // TimeUnit.SECONDS.sleep(2);
                sockets[i].close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        TimeUnit.SECONDS.sleep(120);
    }

}
