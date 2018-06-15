package javac.lang;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private int counter1 = 0;

    private AtomicInteger counter2 = new AtomicInteger(0);

    private Lock cLock = new ReentrantLock();

    public void invokeCounter1() {
        cLock.lock();
        if ("A".equals(Thread.currentThread().getName())) {
            int a[] = {};
            int b = a[1];
            // Thread.yield();
        }
        try {
            counter1 += 1;
            Thread.sleep(100L);
            log(Integer.toString(counter1));
        } catch (InterruptedException e) {
        } finally {
            cLock.unlock();
        }
    }

    public void invokeCounter2() {
        // cLock.lock();
        try {
            counter2.addAndGet(1);
            log(counter2.toString());
        } finally {
            // cLock.unlock();
        }
    }

    public synchronized void invokeCounter3() {
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        counter1 += 1;
        log(Integer.toString(counter1));
    }

    // To illustrate 'All actions in a thread happen-before any other thread
    // successfully returns from a join on that thread. '
    public void invokeCounter4() {
        if ("A".equals(Thread.currentThread().getName())) {
            try {
                Thread.currentThread().join(5000L);
            } catch (Exception ex) {
            } finally {

            }
        }
        counter1 += 1;
        log(Integer.toString(counter1));
    }

    public static void log(String counter) {
        System.out.println(Thread.currentThread().getName() + " is running, value of counter is : " + counter
                + " ========= " + System.currentTimeMillis());
    }

}
