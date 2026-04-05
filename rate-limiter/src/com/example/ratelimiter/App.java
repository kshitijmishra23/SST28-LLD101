package com.example.ratelimiter;

/**
 * Demo showing the full flow:
 *   Client → InternalService → (rate limiter check) → ExternalAPI
 *
 * We test with user T1 who is allowed 5 external calls per minute.
 * Some requests don't need the external call at all, so they bypass the limiter.
 */
public class App {

    public static void main(String[] args) {

        // --- configuration ---
        int maxRequests = 5;
        long windowMillis = 60_000; // 1 minute
        RateLimitConfig config = new RateLimitConfig(maxRequests, windowMillis);

        // --- pick an algorithm (swap this one line to switch strategies) ---
        System.out.println("========== FIXED WINDOW DEMO ==========\n");
        runDemo(RateLimiterFactory.create(RateLimiterFactory.Algorithm.FIXED_WINDOW, config));

        System.out.println("\n========== SLIDING WINDOW DEMO ==========\n");
        runDemo(RateLimiterFactory.create(RateLimiterFactory.Algorithm.SLIDING_WINDOW, config));
    }

    private static void runDemo(RateLimiter limiter) {
        ExternalApiClient externalApi = new ExternalApiClient("PaidSmsGateway");
        InternalService service = new InternalService(limiter, externalApi);

        String customer = "T1";

        // request 1-3: need external call → should be ALLOWED
        for (int i = 1; i <= 3; i++) {
            String result = service.handleRequest(customer, "req-" + i, true);
            System.out.println("  Result: " + result + "\n");
        }

        // request 4: does NOT need external call → no rate limiting involved
        String result4 = service.handleRequest(customer, "req-4-local", false);
        System.out.println("  Result: " + result4 + "\n");

        // request 5-6: need external call → should be ALLOWED (quota was 5, used 3 so far)
        for (int i = 5; i <= 6; i++) {
            String result = service.handleRequest(customer, "req-" + i, true);
            System.out.println("  Result: " + result + "\n");
        }

        // request 7-8: need external call → should be DENIED (quota of 5 exhausted)
        for (int i = 7; i <= 8; i++) {
            String result = service.handleRequest(customer, "req-" + i, true);
            System.out.println("  Result: " + result + "\n");
        }
    }
}
