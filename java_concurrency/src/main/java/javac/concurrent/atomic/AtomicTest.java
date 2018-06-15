package javac.concurrent.atomic;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    public static final int THREADS_COUNT = 20;

    public static AtomicInteger atomicRace = new AtomicInteger();

    public static volatile int intRace = 0;

    public static void atomicIncrease() {
        atomicRace.incrementAndGet();
    }

    public static void nonAtomicIncrease() {
        intRace = intRace + 1;
    }

    public static void countDownLatchTest(boolean atomicUpdate) throws Exception {
        CountDownLatch startGate = new CountDownLatch(1);// If we like to invoke
                                                         // all threads at same
                                                         // time, we need to use
                                                         // this latch
        CountDownLatch endGate = new CountDownLatch(THREADS_COUNT);
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    try {
                        startGate.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < 10000; i++) {
                        invokeIncrease(atomicUpdate);
                    }
                    endGate.countDown();
                }
            });
            threads[i].start();
        }
        startGate.countDown();
        endGate.await();
        printResult(atomicUpdate);
    }

    public static void cylicbarrierTest(boolean atomicUpdate) throws Exception {
        CyclicBarrier cb = new CyclicBarrier(THREADS_COUNT, new Runnable() {
            public void run() {
                printResult(atomicUpdate);
            }
        });
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        invokeIncrease(atomicUpdate);
                    }
                    try {
                        cb.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }
    }

    public static void yieldTest(boolean atomicUpdate) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        invokeIncrease(atomicUpdate);
                    }
                }
            });
            threads[i].start();
            // try {
            // threads[i].join();//make threads to run one by one
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }

            // while(Thread.activeCount() > 1) {//control number of active
            // thread
            // Thread.yield();
            // }
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        printResult(atomicUpdate);
    }

    private static void invokeIncrease(boolean atomicUpdate) {
        if (atomicUpdate) {
            atomicIncrease();
        } else {
            nonAtomicIncrease();
        }
    }

    private static void printResult(boolean atomicUpdate) {
        if (atomicUpdate) {
            System.out.println(atomicRace);
        } else {
            System.out.println(intRace);
        }
    }

    public static void main(String[] args) throws Exception {
        // countDownLatchTest();
        // cylicbarrierTest(false);
        yieldTest(false);
    }

}
