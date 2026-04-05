package com.example.cache;

/**
 * Decides which cache node is responsible for a given key.
 * We start with a simple modulo-based approach, but the interface
 * lets us swap in consistent hashing or anything else later.
 */
public interface DistributionStrategy {
    int getNodeIndex(String key, int totalNodes);
}
