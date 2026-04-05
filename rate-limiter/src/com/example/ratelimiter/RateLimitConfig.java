package com.example.ratelimiter;

/**
 * Simple POJO that holds the "how many requests" and "in what time window" config.
 * We keep this separate so any algorithm can read the same config object.
 */
public class RateLimitConfig {

    private final int maxRequests;       // e.g. 100
    private final long windowSizeMillis; // e.g. 60_000 for 1 minute

    public RateLimitConfig(int maxRequests, long windowSizeMillis) {
        if (maxRequests <= 0) {
            throw new IllegalArgumentException("maxRequests must be positive");
        }
        if (windowSizeMillis <= 0) {
            throw new IllegalArgumentException("windowSizeMillis must be positive");
        }
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public long getWindowSizeMillis() {
        return windowSizeMillis;
    }

    @Override
    public String toString() {
        return maxRequests + " requests per " + windowSizeMillis + "ms";
    }
}
