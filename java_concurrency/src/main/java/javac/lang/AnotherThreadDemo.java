package javac.lang;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AnotherThreadDemo extends Thread {

    private AnotherCounter ac;

    private Lock demoLK;

    private Object objLock = new Object();

    public AnotherThreadDemo(AnotherCounter ac, Lock lock) {
        this.ac = ac;
        this.demoLK = lock;
    }

    public void run() {
        try {
            // demoLK.lock();
            ac.counter(objLock);
        } finally {
            // demoLK.unlock();
        }

    }

    public static void main(String[] args) {
        AnotherCounter ac = new AnotherCounter();
        Lock lock = new ReentrantLock();
        Thread threadC = new AnotherThreadDemo(ac, lock);
        threadC.setName("C+");
        threadC.start();
        Thread threadD = new AnotherThreadDemo(ac, lock);
        threadD.setName("D-");
        threadD.start();
    }

}
