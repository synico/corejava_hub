package javac.concurrent.collection.list;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    
    static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        for(int i = 0; i < 1000; i++) {
            String kv = Integer.toString(i);
            map.put(kv, kv);
        }
    }

}
