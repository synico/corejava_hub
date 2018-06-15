package javac.concurrent.collection.map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashMapDemo {
    
    static ConcurrentMap<String, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        for(int i = 0; i < 10000; i++) {
            String kv = Integer.toString(i);
            map.put(kv, kv);
        }
    }

}
