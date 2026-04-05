package com.example.ratelimiter;

/**
 * Core abstraction for rate limiting.
 * Any algorithm (fixed window, sliding window, token bucket, etc.)
 * just needs to implement this single method.
 */
public interface RateLimiter {

    /**
     * Checks if the request identified by the given key is allowed
     * under the current rate limit, and if yes, consumes one unit of quota.
     *
     * @param key a string that identifies who/what is being limited
     *            (e.g. "customer:T1", "tenant:acme", "apikey:xyz")
     * @return true if the call is permitted, false if quota is exhausted
     */
    boolean allowRequest(String key);
}
