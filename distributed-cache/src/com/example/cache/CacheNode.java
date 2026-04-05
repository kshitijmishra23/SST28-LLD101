package com.example.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * A single cache node with a fixed capacity.
 * Uses an EvictionPolicy to decide what to throw away when full.
 */
public class CacheNode {

    private final int id;
    private final int capacity;
    private final Map<String, String> storage;
    private final EvictionPolicy<String> evictionPolicy;

    public CacheNode(int id, int capacity, EvictionPolicy<String> evictionPolicy) {
        this.id = id;
        this.capacity = capacity;
        this.storage = new HashMap<>();
        this.evictionPolicy = evictionPolicy;
    }

    public String get(String key) {
        if (!storage.containsKey(key)) {
            return null; // cache miss
        }
        evictionPolicy.keyAccessed(key);
        return storage.get(key);
    }

    public void put(String key, String value) {
        // if key already exists, just update
        if (storage.containsKey(key)) {
            storage.put(key, value);
            evictionPolicy.keyAccessed(key);
            return;
        }

        // need to evict if we're at capacity
        if (storage.size() >= capacity) {
            String evictedKey = evictionPolicy.evict();
            storage.remove(evictedKey);
            System.out.println("  [Node " + id + "] Evicted key: \"" + evictedKey + "\"");
        }

        storage.put(key, value);
        evictionPolicy.keyAccessed(key);
    }

    public boolean containsKey(String key) {
        return storage.containsKey(key);
    }

    public int getId() {
        return id;
    }

    public int size() {
        return storage.size();
    }

    public int getCapacity() {
        return capacity;
    }
}
