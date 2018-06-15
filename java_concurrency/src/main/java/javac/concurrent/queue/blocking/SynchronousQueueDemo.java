package javac.concurrent.queue.blocking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {

    private static final BlockingQueue<String> syncQueue = new SynchronousQueue<String>();

    class Consumer implements Runnable {

        @Override
        public void run() {
            for (;;) {
                String rst = null;
                // option1
                try {
                    rst = syncQueue.poll(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // option2
                // try {
                // rst = syncQueue.take();
                // } catch (InterruptedException e) {
                // e.printStackTrace();
                // }
                if (rst != null) {
                    System.out.println(rst);
                }
            }
        }

    }

    class Producer implements Runnable {

        @Override
        public void run() {
            for (;;) {
                // option1
                try {
                    syncQueue.offer("test: " + System.currentTimeMillis(), 1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // option2
                // try {
                // syncQueue.put("test: " + System.currentTimeMillis());
                // } catch (InterruptedException e) {
                // e.printStackTrace();
                // }
            }
        }

    }

    public void test() {
        Consumer con = new Consumer();
        Producer pro = new Producer();

        new Thread(pro).start();
        new Thread(con).start();
        ;
    }

    public static void main(String[] args) {
        SynchronousQueueDemo demo = new SynchronousQueueDemo();
        demo.test();
    }

}
