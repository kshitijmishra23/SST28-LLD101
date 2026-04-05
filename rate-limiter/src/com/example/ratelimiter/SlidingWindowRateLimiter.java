package com.example.ratelimiter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Sliding Window Counter algorithm.
 *
 * How it works:
 *   - Combines two adjacent fixed windows and uses a weighted average
 *     based on how far we are into the current window.
 *   - Example: if the previous window had 8 hits, current window has 3 hits,
 *     and we are 40% into the current window, the effective count is:
 *         8 * (1 - 0.40) + 3 = 4.8 + 3 = 7.8
 *   - If effective count < maxRequests → allow, else deny.
 *
 * Pros:  smooths out the boundary spike that fixed window suffers from,
 *        still O(1) per check, only slightly more memory than fixed window.
 * Cons:  the count is an approximation (good enough in practice),
 *        slightly more logic than plain fixed window.
 */
public class SlidingWindowRateLimiter implements RateLimiter {

    private final RateLimitConfig config;

    private final ConcurrentHashMap<String, SlidingData> dataMap = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(RateLimitConfig config) {
        this.config = config;
    }

    @Override
    public boolean allowRequest(String key) {
        long now = System.currentTimeMillis();
        long windowSize = config.getWindowSizeMillis();
        long currentWindowStart = (now / windowSize) * windowSize;
        // how far into the current window we are (0.0 to 1.0)
        double elapsedRatio = (double) (now - currentWindowStart) / windowSize;

        SlidingData data = dataMap.compute(key, (k, existing) -> {
            if (existing == null) {
                return new SlidingData(currentWindowStart);
            }
            // if the current window has moved ahead, shift things over
            existing.advanceTo(currentWindowStart, windowSize);
            return existing;
        });

        // synchronize on per-key data so the read-check-write is atomic
        synchronized (data) {
            // weighted count from previous window + actual count in current window
            double effectiveCount = data.previousCount * (1.0 - elapsedRatio)
                                  + data.currentCount;

            if (effectiveCount < config.getMaxRequests()) {
                data.currentCount++;
                return true;
            }
            return false;
        }
    }

    /**
     * Keeps counters for two consecutive windows per key.
     */
    private static class SlidingData {
        long currentWindowStart;
        int currentCount;
        int previousCount;

        SlidingData(long windowStart) {
            this.currentWindowStart = windowStart;
            this.currentCount = 0;
            this.previousCount = 0;
        }

        /**
         * If time has moved past our recorded window, roll the counters forward.
         */
        void advanceTo(long newWindowStart, long windowSize) {
            if (newWindowStart == currentWindowStart) {
                return; // still in the same window
            }
            long gap = newWindowStart - currentWindowStart;
            if (gap == windowSize) {
                // exactly one window ahead → current becomes previous
                previousCount = currentCount;
            } else {
                // more than one window has passed → previous data is too old
                previousCount = 0;
            }
            currentCount = 0;
            currentWindowStart = newWindowStart;
        }
    }
}
