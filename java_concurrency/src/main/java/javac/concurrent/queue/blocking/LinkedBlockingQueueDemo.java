package javac.concurrent.queue.blocking;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Producer and Consumer
 * @author 907729
 *
 */

public class LinkedBlockingQueueDemo {
    
    private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);
    
    class Producer1 implements Runnable {

        @Override
        public void run() {
            for(int i = 0; i < 50; i++) {
                try {
                    queue.put("task - " + i);
                    System.out.println("put element to " + i + " queue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    class Consumer1 implements Runnable {

        @Override
        public void run() {
            System.out.println("start to trying consumer element in Consumer1");
            while(true) {
                try {
                    String task = queue.take();
                    System.out.println("Consumer1 | take element " + task + " from queue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
    class Consumer2 implements Runnable {

        @Override
        public void run() {
            System.out.println("start to trying consumer element in Consumer2");
            while(true) {
                try {
                    String task = queue.take();
                    System.out.println("Consumer2 | take element " + task + " from queue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }

    public static void main(String[] args) {
        LinkedBlockingQueueDemo demo = new LinkedBlockingQueueDemo();
        Thread producer1 = new Thread(demo.new Producer1());
        producer1.setName("producer1 thread");
        
        Thread consumer1 = new Thread(demo.new Consumer1());
        consumer1.setName("consumer1 thread");
        Thread consumer2 = new Thread(demo.new Consumer2());
        consumer2.setName("consumer2 thread");
        
        consumer1.start();
        consumer2.start();
        producer1.start();
    }

}
