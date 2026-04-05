package com.example.cache;

public class App {
    public static void main(String[] args) {

        // ======== Setup ========
        Database db = new InMemoryDatabase();
        DistributionStrategy strategy = new ModuloDistributionStrategy();
        EvictionPolicyFactory<String> evictionFactory = LRUEvictionPolicy::new;

        // 3 cache nodes, each can hold 2 entries
        DistributedCache cache = new DistributedCache(3, 2, strategy, evictionFactory, db);

        // pre-populate database with some data (simulating existing records)
        db.put("user:1", "Alice");
        db.put("user:2", "Bob");
        db.put("user:3", "Charlie");
        db.put("user:4", "Diana");
        db.put("user:5", "Eve");

        // ======== Demo 1: Cache miss -> fetches from DB ========
        System.out.println("=== Cache Miss Demo ===");
        String val1 = cache.get("user:1");
        System.out.println("  Result: " + val1);

        String val2 = cache.get("user:2");
        System.out.println("  Result: " + val2);

        // ======== Demo 2: Cache hit ========
        System.out.println("\n=== Cache Hit Demo ===");
        String val1Again = cache.get("user:1");
        System.out.println("  Result: " + val1Again);

        // ======== Demo 3: Put new data ========
        System.out.println("\n=== Put Demo ===");
        cache.put("user:6", "Frank");
        cache.put("user:7", "Grace");

        // ======== Demo 4: Eviction (node capacity = 2) ========
        System.out.println("\n=== Eviction Demo ===");
        // keep adding keys that land on the same node to trigger eviction
        cache.get("user:3");
        cache.get("user:4");
        cache.get("user:5");

        // ======== Demo 5: Read back after eviction ========
        System.out.println("\n=== Post-Eviction Reads ===");
        cache.get("user:1"); // might be evicted, will re-fetch from DB
        cache.get("user:5"); // should be a hit

        // ======== Node stats ========
        System.out.println("\n=== Node Stats ===");
        for (int i = 0; i < cache.getNodeCount(); i++) {
            CacheNode node = cache.getNode(i);
            System.out.println("  Node " + node.getId()
                    + ": " + node.size() + "/" + node.getCapacity() + " entries");
        }
    }
}
