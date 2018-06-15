package com.ds.map.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    
    private static final long serialVersionUID = 1L;

    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return super.size() > 100;
    }

}
