package javac.map;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Not sure this demo is correct, need to be proven
 * 
 * @author 907729
 *
 */
public class SortedMapDemo {

    public static final List<String> sink = new ArrayList<String>();

    private volatile Map<String, String> treeMap = new TreeMap<String, String>();

    private Map<String, String> skipListMap = new ConcurrentSkipListMap<String, String>();

    private final static Lock tmLock = new ReentrantLock();

    private static void log(String msg) {
        System.out.println(msg + "|" + System.currentTimeMillis());
    }

    static {
        for (int i = 0; i < 1000000; i++) {
            BigDecimal n = new BigDecimal(Math.random() * 100);
            sink.add(n.round(MathContext.DECIMAL32).toString());
        }
    }

    class TreeMapUpdate extends Thread {

        private Lock wl;

        public TreeMapUpdate(Lock lock) {
            wl = lock;
        }

        public void run() {
            log(Thread.currentThread().getName() + " is starting");
            long start = System.currentTimeMillis();
            wl.lock();
            try {
                for (String x : sink) {
                    treeMap.put(x, x);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                wl.unlock();
            }
            long end = System.currentTimeMillis();
            log(Thread.currentThread().getName() + " takes [" + (end - start) + "]");
            log("size of treeMap: " + treeMap.size());
            log("---------------------------------------");
        }
    }

    class SkipListMapUpdate extends Thread {

        public void run() {
            log(Thread.currentThread().getName() + " is starting");
            long start = System.currentTimeMillis();
            try {
                for (String x : sink) {
                    skipListMap.put(x, x);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
            long end = System.currentTimeMillis();
            log(Thread.currentThread().getName() + " takes [" + (end - start) + "]");
            log("size of skipListMap: " + skipListMap.size());
            log("---------------------------------------");
        }
    }

    public void startNewThread(String tName, boolean byLock) {
        if (byLock) {
            Thread A = new TreeMapUpdate(tmLock);
            A.setName(tName);
            A.start();
        } else {
            Thread B = new SkipListMapUpdate();
            B.setName(tName);
            B.start();
        }
    }

    public static void main(String[] args) {
        SortedMapDemo demo = new SortedMapDemo();
        demo.startNewThread("thread-A", true);
        demo.startNewThread("thread-B", true);

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        demo.startNewThread("thread-C", false);
        demo.startNewThread("threadD", false);
    }

}
