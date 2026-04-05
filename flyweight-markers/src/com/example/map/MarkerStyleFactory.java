package com.example.map;

import java.util.HashMap;
import java.util.Map;

// flyweight factory - caches MarkerStyle objects so we dont create duplicates
public class MarkerStyleFactory {

    private final Map<String, MarkerStyle> cache = new HashMap<>();

    public MarkerStyle get(String shape, String color, int size, boolean filled) {
        // using same format as toString for the key
        String key = shape + "|" + color + "|" + size + "|" + (filled ? "F" : "O");

        // check if we already have this style cached
        MarkerStyle style = cache.get(key);
        if (style == null) {
            style = new MarkerStyle(shape, color, size, filled);
            cache.put(key, style);
        }
        return style;
    }

    public int cacheSize() {
        return cache.size();
    }
}
