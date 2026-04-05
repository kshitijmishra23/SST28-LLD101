package com.example.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Fixed Window Counter algorithm.
 *
 * How it works:
 *   - Time is divided into fixed-size windows (e.g. every 60 seconds).
 *   - Each key gets a counter that resets at the start of every new window.
 *   - If counter < maxRequests → allow and increment.
 *   - Otherwise → deny.
 *
 * Pros:  very simple, low memory, O(1) per check.
 * Cons:  boundary spike problem – a burst at the end of window N and
 *        start of window N+1 can let through ~2x the limit.
 */
public class FixedWindowRateLimiter implements RateLimiter {

    private final RateLimitConfig config;

    // each key maps to its own WindowData
    private final ConcurrentHashMap<String, WindowData> windows = new ConcurrentHashMap<>();

    public FixedWindowRateLimiter(RateLimitConfig config) {
        this.config = config;
    }

    @Override
    public boolean allowRequest(String key) {
        long now = System.currentTimeMillis();
        long currentWindowStart = (now / config.getWindowSizeMillis()) * config.getWindowSizeMillis();

        // get-or-create the window data for this key
        WindowData data = windows.compute(key, (k, existing) -> {
            if (existing == null || existing.windowStart != currentWindowStart) {
                // first request for this key, or window has rolled over
                return new WindowData(currentWindowStart);
            }
            return existing;
        });

        // try to increment; deny if we've hit the cap
        int count = data.counter.incrementAndGet();
        return count <= config.getMaxRequests();
    }

    /**
     * Holds the counter for a single fixed window.
     */
    private static class WindowData {
        final long windowStart;
        final AtomicInteger counter;

        WindowData(long windowStart) {
            this.windowStart = windowStart;
            this.counter = new AtomicInteger(0);
        }
    }
}
