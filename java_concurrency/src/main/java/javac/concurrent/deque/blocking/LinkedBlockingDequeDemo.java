package javac.concurrent.deque.blocking;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class LinkedBlockingDequeDemo {

    private BlockingDeque<String> lbDeque = new LinkedBlockingDeque<String>(50);

    class Consumer1 implements Runnable {

        @Override
        public void run() {
            System.out.println("Consumer1 ||| size of lbDeque: " + lbDeque.size());
            int sizeOfDeque = lbDeque.size();
            try {
                // TimeUnit.MILLISECONDS.sleep(100);
                for (int i = 0; i < sizeOfDeque; i++) {
                    String rst = lbDeque.take();
                    System.out.println("Consumer1 take element from BlockingDeque: " + rst);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    class Consumer2 implements Runnable {

        @Override
        public void run() {
            int sizeOfDeque = lbDeque.size();
            try {
                for (int i = 0; i < sizeOfDeque; i++) {
                    String rst = lbDeque.take();
                    System.out.println("Consumer2 take element from BlockingDeque: " + rst);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    class Producer implements Runnable {

        @Override
        public void run() {
            try {
                for (int i = 0; i < 1000; i++) {
                    // TimeUnit.MILLISECONDS.sleep(1);
                    lbDeque.put("test: " + i);
                    System.out.println("Producer put element \"test:" + i + " to BlockingDeque");
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("++++++++++++size of lbDeque: " + lbDeque.size());
        }

    }

    private void test() {
        Producer producer = new Producer();
        new Thread(producer).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Consumer1 consumer1 = new Consumer1();
        Consumer2 consumer2 = new Consumer2();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }

    public static void main(String[] args) {
        LinkedBlockingDequeDemo demo = new LinkedBlockingDequeDemo();
        demo.test();
    }

}
