package com.example.ratelimiter;

/**
 * The internal service that handles business logic.
 * It only consults the rate limiter when it actually needs to call the external API.
 * This is the key design point: rate limiting is NOT on incoming requests,
 * it's right before the paid external call.
 */
public class InternalService {

    private final RateLimiter rateLimiter;
    private final ExternalApiClient externalApi;

    public InternalService(RateLimiter rateLimiter, ExternalApiClient externalApi) {
        this.rateLimiter = rateLimiter;
        this.externalApi = externalApi;
    }

    /**
     * Processes a client request. The external call only happens when
     * business logic decides it's needed, and even then only if the
     * rate limiter allows it.
     *
     * @param customerId  used as the rate limiting key
     * @param requestData the incoming request payload
     * @param needsExternalCall whether business logic determined an external call is needed
     * @return result string
     */
    public String handleRequest(String customerId, String requestData, boolean needsExternalCall) {
        System.out.println("Processing request for " + customerId + ": " + requestData);

        // step 1: run some business logic (always happens)
        String processed = "processed_" + requestData;

        // step 2: check if we even need the external resource
        if (!needsExternalCall) {
            System.out.println("  No external call needed — skipping rate limiter entirely.");
            return processed;
        }

        // step 3: we need the external resource, so check the rate limiter first
        String rateLimitKey = "customer:" + customerId;

        if (!rateLimiter.allowRequest(rateLimitKey)) {
            System.out.println("  RATE LIMITED — external call denied for " + customerId);
            return "rate_limited";
        }

        // step 4: allowed — go ahead and call the paid API
        String externalResult = externalApi.call(requestData);
        return processed + " | external=" + externalResult;
    }
}
