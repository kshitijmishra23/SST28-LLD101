package com.example.cache;

import java.util.LinkedHashSet;

/**
 * LRU eviction — the key that hasn't been used for the longest
 * time gets kicked out first.
 *
 * Internally we keep a LinkedHashSet so that insertion order is
 * maintained. On every access we move the key to the end (most recent).
 * Eviction simply removes from the front (least recent).
 */
public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {

    private final LinkedHashSet<K> order = new LinkedHashSet<>();

    @Override
    public void keyAccessed(K key) {
        // move to end = most recently used
        order.remove(key);
        order.add(key);
    }

    @Override
    public K evict() {
        K oldest = order.iterator().next();
        order.remove(oldest);
        return oldest;
    }

    @Override
    public void remove(K key) {
        order.remove(key);
    }
}
