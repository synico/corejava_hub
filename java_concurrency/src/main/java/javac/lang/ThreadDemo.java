package javac.lang;

import java.io.IOException;

public class ThreadDemo extends Thread {

    private Counter counter;

    public ThreadDemo(Counter c) {
        this.counter = c;
    }

    public void run() {
        if ("A".equals(Thread.currentThread().getName())) {
            try {
                // Thread.currentThread().interrupt();
                // join(10000L);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // return;
        }
        System.out.println("State of current thread: " + Thread.currentThread().getName() + " | "
                + Thread.currentThread().getState().toString());
        for (int i = 0; i < 20; i++) {
            this.counter.invokeCounter4();
        }
    }

    public static void main(String[] args) {
        Counter c = new Counter();
        ThreadDemo threadA = new ThreadDemo(c);
        threadA.setName("A");
        // try {
        // threadA.join(10L);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        threadA.start();
        ThreadDemo threadB = new ThreadDemo(c);
        threadB.setName("B");
        threadB.setPriority(MAX_PRIORITY);
        threadB.start();
    }

}
