package javac.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestHarness {

    public long timeTasks(int nThreads, final Runnable task) {

        CyclicBarrier cb = new CyclicBarrier(nThreads, new Runnable() {
            public void run() {
                System.out.println("completed");
            }
        });

        long start = System.currentTimeMillis();

        for (int i = 0; i < nThreads; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        task.run();
                        System.out.println(Thread.currentThread().getName() + " will be in await");
                        Thread.sleep(1000L);
                        int rst = cb.await();
                        System.out.println(Thread.currentThread().getName() + "ALL THREADS COMES HERE, rst: " + rst);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }, Integer.toString(i)).start();
        }

        long end = System.currentTimeMillis();

        return end - start;
    }

    public static void main(String[] args) {
        TestHarness th = new TestHarness();
        try {
            long totalTime = th.timeTasks(5, new Runnable() {
                public void run() {
                    System.out.println("xxxx==========" + Thread.currentThread().getName());
                }
            });
            System.out.println("Total time: " + totalTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
