package com.example.cache;

/**
 * Factory that creates a fresh EvictionPolicy for each cache node.
 * This way the caller decides which eviction strategy to use,
 * and we can swap LRU for MRU/LFU without touching DistributedCache.
 */
public interface EvictionPolicyFactory<K> {
    EvictionPolicy<K> create();
}
