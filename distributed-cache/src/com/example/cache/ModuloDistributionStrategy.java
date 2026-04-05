package com.example.cache;

/**
 * Simplest distribution: hash the key and mod by number of nodes.
 * Works fine when the node count is fixed. If nodes are added/removed
 * frequently, consistent hashing would be a better fit.
 */
public class ModuloDistributionStrategy implements DistributionStrategy {

    @Override
    public int getNodeIndex(String key, int totalNodes) {
        int hash = Math.abs(key.hashCode());
        return hash % totalNodes;
    }
}
