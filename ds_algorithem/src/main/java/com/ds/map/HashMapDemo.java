package com.ds.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {

    public static void main(String[] args) {
        HashMap<String, String> hm = new HashMap<>(32);
        for (int i = 0; i < 43; i++) {
            String key = Integer.toString(i);
            hm.put(key, key);
        }

        for (int i = 43; i < 60; i++) {
            String key = Integer.toString(i);
            int h;
            System.out.println((key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16));
            hm.put(key, key);
        }

        System.out.println("start to loop HashMap(hm)----------------");
        for (Map.Entry<String, String> entry : hm.entrySet()) {
            System.out.println(entry.getKey());
        }
        System.out.println("end to loop HashMap(hm)----------------");

        // hm.entrySet().parallelStream().forEach(System.out::println);
    }

}
