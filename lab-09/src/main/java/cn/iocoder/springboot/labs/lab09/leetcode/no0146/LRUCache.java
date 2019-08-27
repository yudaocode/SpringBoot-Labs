package cn.iocoder.springboot.labs.lab09.leetcode.no0146;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

    private Map<Integer, Integer> cache;

    public LRUCache(int capacity) {
        this.cache = new LinkedHashMap<Integer, Integer>((int) Math.ceil(capacity / 0.75f) + 1, 0.75f, true) {

            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }

        };
    }

    public int get(int key) {
        Integer val = cache.get(key);
        return val != null ? val : -1;
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }

}
