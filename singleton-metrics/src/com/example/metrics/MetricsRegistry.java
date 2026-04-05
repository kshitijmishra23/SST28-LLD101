package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Global metrics registry — proper thread-safe singleton.
 *
 * Uses static holder idiom for lazy, thread-safe initialization.
 * Blocks reflection attacks and preserves singleton across serialization.
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<String, Long> counters = new HashMap<>();

    // Flag to block reflection-based second construction
    private static boolean instanceCreated = false;

    // Private constructor — blocks reflection attack
    private MetricsRegistry() {
        synchronized (MetricsRegistry.class) {
            if (instanceCreated) {
                throw new IllegalStateException("Singleton already constructed — use getInstance()");
            }
            instanceCreated = true;
        }
    }

    // Static holder idiom: lazy, thread-safe, no synchronization overhead
    private static class Holder {
        private static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    public static MetricsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    // Preserve singleton on deserialization
    @Serial
    private Object readResolve() {
        return getInstance();
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }
}
