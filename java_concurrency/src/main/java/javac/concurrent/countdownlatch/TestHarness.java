package javac.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestHarness {

    private Object obj = new Object();

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread(Integer.toString(i)) {
                public void run() {
                    // synchronized(obj) {
                    try {
                        printLog("Thread Name: " + this.getName() + " will invoke startGate.await()");
                        startGate.await();
                        try {
                            printLog("Thread Name: " + this.getName() + " will run");
                            task.run();
                        } finally {
                            endGate.countDown();
                            printLog(this.getName() + " endGate.countDown()");
                        }
                    } catch (InterruptedException ignored) {
                        ignored.printStackTrace();
                    }
                }
                // }
            };
            t.start();
        }

        long start = System.currentTimeMillis();
        printLog("WILL INVOKE startGate.countDown()");
        // Thread.sleep(5000L);
        startGate.countDown();
        printLog("WILL INVOKE endGate.await()");
        endGate.await();
        long end = System.currentTimeMillis();

        return end - start;
    }

    private static void printLog(String msg) {
        System.out.println(Thread.currentThread().getName() + " | " + msg);
    }

    public static void main(String args[]) {
        TestHarness th = new TestHarness();
        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(100L);
                    printLog("x: " + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        try {
            long totalTime = th.timeTasks(10, thread);
            printLog("Total time: " + totalTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
