package javac.lang;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AnotherCounter {

    private volatile int counter = 0;

    private Lock lock = new ReentrantLock();

    private static Object objLock = new Object();

    public void counter(Object obj) {
        try {
            lock.lock();
            if ("C+".equals(Thread.currentThread().getName())) {
                for (int i = 0; i < 40; i++) {
                    counter4();
                }
            } else {
                for (int i = 0; i < 40; i++) {
                    counter5(obj);
                }
            }
            // for(int i=0;i<40;i++) {
            // nolockCounter();
            // }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    private void nolockCounter() {
        counter += 1;
        log(Integer.toString(counter));
    }

    // lock by ReentrantLock
    private void counter1() {
        try {
            lock.lock();
            counter += 1;
            log(Integer.toString(counter));
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    // lock by synchronized
    private synchronized void counter2() {
        counter += 1;
        log(Integer.toString(counter));
    }

    // lock by ReentrantLock
    private void counter3() {
        try {
            lock.lock();
            counter += 1;
            log(Integer.toString(counter));
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    //
    private synchronized void counter4() {
        try {
            Thread.sleep(100L);
        } catch (Exception e) {

        }
        counter += 1;
        log(Integer.toString(counter));
    }

    private void counter5(Object obj) {
        synchronized (obj) {
            counter += 1;
            log(Integer.toString(counter));
        }
    }

    public static void log(String counter) {
        System.out.println(Thread.currentThread().getName() + " is running, value of counter is : " + counter
                + " ========= " + System.currentTimeMillis());
    }

}
