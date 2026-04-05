package com.example.ratelimiter;

/**
 * Factory that creates the right RateLimiter based on a chosen algorithm name.
 * Adding a new algorithm is just: implement RateLimiter + add a case here.
 */
public class RateLimiterFactory {

    public enum Algorithm {
        FIXED_WINDOW,
        SLIDING_WINDOW
        // future: TOKEN_BUCKET, LEAKY_BUCKET, SLIDING_LOG
    }

    public static RateLimiter create(Algorithm algorithm, RateLimitConfig config) {
        switch (algorithm) {
            case FIXED_WINDOW:
                return new FixedWindowRateLimiter(config);
            case SLIDING_WINDOW:
                return new SlidingWindowRateLimiter(config);
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
        }
    }
}
