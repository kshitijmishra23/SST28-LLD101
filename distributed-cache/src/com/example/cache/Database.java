package com.example.cache;

/**
 * Abstraction for the underlying data store.
 * In a real system this would talk to MySQL/Postgres etc.
 * Here we just use an in-memory map to keep things simple.
 */
public interface Database {
    String get(String key);
    void put(String key, String value);
}
