package com.example.cache;

/**
 * Strategy interface for cache eviction.
 * Right now we only have LRU, but tomorrow we can plug in
 * MRU, LFU, or anything else without touching existing code.
 */
public interface EvictionPolicy<K> {

    /** Called whenever a key is accessed (get or put). */
    void keyAccessed(K key);

    /** Returns (and removes) the key that should be evicted next. */
    K evict();

    /** Remove a specific key from tracking (e.g. on explicit delete). */
    void remove(K key);
}
