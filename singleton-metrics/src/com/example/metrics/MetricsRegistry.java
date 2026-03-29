package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * INTENTION: Global metrics registry (should be a Singleton).
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - Constructor is public -> anyone can create instances.
 * - getInstance() is lazy but NOT thread-safe -> can create multiple instances.
 * - Reflection can call the constructor to create more instances.
 * - Serialization can create a new instance when deserialized.
 *
 * TODO (student):
 *  1) Make it a proper lazy, thread-safe singleton (private ctor)
 *  2) Block reflection-based multiple construction
 *  3) Preserve singleton on serialization (readResolve)
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // here i made it volatile , as without it a another thread might see a partially constructed object.
    private static volatile MetricsRegistry INSTANCE; // BROKEN: not volatile, not thread-safe
    private final Map<String, Long> counters = new HashMap<>();

    // BROKEN: should be private and should prevent second construction
    private MetricsRegistry() {
        // intentionally empty
        // her constructor is private , but still someone can still make a new instance.
        // so now here we will modify thr constructor by having a check , so thar no second object canbe created .
         if(INSTANCE == null){
             throw new RuntimeException("Use getInstance() to create");
         }
    }



    // BROKEN: racy lazy init; two threads can create two instances
    public static MetricsRegistry getInstance() {
        // Here I am doing double-check here  by this only one thread will come first and will create object .
       if(INSTANCE == null){
           synchronized (MetricsRegistry.class){
               if (INSTANCE == null) {
                   INSTANCE = new MetricsRegistry();
               }
           }
       }
        return INSTANCE;
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

    // TODO: implement readResolve() to preserve singleton on deserialization
    // Well , here we have the issue of serialisation and , we have to solve it ,
    // so we will create a method readResolve() , which will replace the deserialized object with this one instead.
    @Serial
    protected Object readResolve() {
        return getInstance();
    }
}
