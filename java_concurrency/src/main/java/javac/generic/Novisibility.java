package javac.generic;

import java.util.concurrent.TimeUnit;

public class Novisibility {

    private static boolean ready;
    private static int number;

    private class ReaderThread extends Thread {
        public void run() {
            if (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        Novisibility ns = new Novisibility();
        (ns.new ReaderThread()).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ready = true;
        number = 42;
    }

}
