package com.example.cache;

import java.util.ArrayList;
import java.util.List;

/**
 * The main entry point — coordinates between cache nodes and the database.
 *
 * How it works:
 *   1. A key comes in via get() or put().
 *   2. The DistributionStrategy picks which CacheNode handles that key.
 *   3. On a cache miss (get), we fetch from the Database, store in cache, then return.
 *   4. On put, we write to both the cache node and the database (write-through).
 */
public class DistributedCache {

    private final List<CacheNode> nodes;
    private final DistributionStrategy distributionStrategy;
    private final Database database;

    public DistributedCache(int numberOfNodes, int capacityPerNode,
                            DistributionStrategy distributionStrategy,
                            EvictionPolicyFactory<String> evictionFactory,
                            Database database) {
        this.distributionStrategy = distributionStrategy;
        this.database = database;
        this.nodes = new ArrayList<>();

        for (int i = 0; i < numberOfNodes; i++) {
            // each node gets its own eviction policy instance from the factory
            EvictionPolicy<String> eviction = evictionFactory.create();
            nodes.add(new CacheNode(i, capacityPerNode, eviction));
        }
    }

    /**
     * Get a value by key.
     * Cache hit  -> return from cache.
     * Cache miss -> fetch from DB, store in cache, then return.
     */
    public String get(String key) {
        CacheNode node = getResponsibleNode(key);
        String value = node.get(key);

        if (value != null) {
            System.out.println("  [Cache HIT]  key=\"" + key + "\" from Node " + node.getId());
            return value;
        }

        // cache miss — go to DB
        value = database.get(key);
        if (value != null) {
            System.out.println("  [Cache MISS] key=\"" + key + "\" -> fetched from DB, caching on Node " + node.getId());
            node.put(key, value);
        } else {
            System.out.println("  [Cache MISS] key=\"" + key + "\" -> not found in DB either");
        }
        return value;
    }

    /**
     * Put a key-value pair.
     * Writes to both cache and database (write-through strategy).
     */
    public void put(String key, String value) {
        CacheNode node = getResponsibleNode(key);
        node.put(key, value);
        database.put(key, value);
        System.out.println("  [PUT] key=\"" + key + "\" -> Node " + node.getId());
    }

    /** Uses the distribution strategy to figure out which node owns this key. */
    private CacheNode getResponsibleNode(String key) {
        int index = distributionStrategy.getNodeIndex(key, nodes.size());
        return nodes.get(index);
    }

    // ---- helpers for the demo ----

    public int getNodeCount() {
        return nodes.size();
    }

    public CacheNode getNode(int index) {
        return nodes.get(index);
    }
}
