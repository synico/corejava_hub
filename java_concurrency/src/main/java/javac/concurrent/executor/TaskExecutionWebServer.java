package javac.concurrent.executor;

/**
 * http://www.javaspecialists.eu/tutorials/001-Hacking-together-our-own-ArrayList-in-less-than-10-minutes.html
 */

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskExecutionWebServer {

    private static final int NTHREADS = 10;
    // private static final Executor exec =
    // Executors.newFixedThreadPool(NTHREADS);
    private static final Executor exec = Executors.newCachedThreadPool();
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void handleRequest(Socket conn) {
        System.out.println("Connection from " + conn);
        try (InputStream in = conn.getInputStream(); OutputStream out = conn.getOutputStream()) {
            int data;
            while ((data = in.read()) != -1) {
                out.write(Util.transmogrify(data));
            }
            out.write(conn.getLocalPort());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void log(String msg) {
        System.out.println(msg);
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("received: " + counter.incrementAndGet());
            try (ServerSocket socket = new ServerSocket(8080)) {
                final Socket connection = socket.accept();
                exec.execute(() -> handleRequest(connection));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
