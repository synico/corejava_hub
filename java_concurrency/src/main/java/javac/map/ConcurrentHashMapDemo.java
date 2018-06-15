package javac.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentHashMapDemo {

    private final Map<String, Long> urlCounter = new ConcurrentHashMap<>();
    private final Map<String, AtomicLong> urlCounter2 = new ConcurrentHashMap<>();

    public long increase(String url) {
        Long oldValue = urlCounter.get(url);
        Long newValue = (oldValue == null) ? 1L : oldValue + 1;
        urlCounter.put(url, newValue);
        return newValue;
    }

    public long increase2(String url) {
        AtomicLong oldValue = (urlCounter.get(url) == null) ? new AtomicLong(1L) : new AtomicLong(urlCounter.get(url));
        long newValue = oldValue.addAndGet(1L);
        urlCounter.put(url, newValue);
        return newValue;
    }

    public long increase3(String url) {
        AtomicLong oldValue = urlCounter2.get(url);
        if (oldValue == null) {
            AtomicLong newValue = new AtomicLong(0);
            oldValue = urlCounter2.putIfAbsent(url, newValue);
        }
        return oldValue.incrementAndGet();
    }

    public Long getCounter(String url) {
        return urlCounter.get(url);
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        ConcurrentHashMapDemo demo = new ConcurrentHashMapDemo();
        int callTime = 100000;
        final String url = "http://localhost:8080";
        CountDownLatch latch = new CountDownLatch(callTime);
        for (int i = 0; i < callTime; i++) {
            executor.execute(new Runnable() {
                public void run() {
                    demo.increase2(url);
                    latch.countDown();
                }
            });
        }

        try {
            latch.await();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        executor.shutdown();
        System.out.println("counter: " + demo.getCounter(url));
    }

}
