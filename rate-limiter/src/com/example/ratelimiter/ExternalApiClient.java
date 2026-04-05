package com.example.ratelimiter;

/**
 * Represents any paid external resource (SMS gateway, AI API, payment provider, etc.).
 * In a real system this would make an HTTP call; here we just simulate it.
 */
public class ExternalApiClient {

    private final String providerName;

    public ExternalApiClient(String providerName) {
        this.providerName = providerName;
    }

    public String call(String payload) {
        // simulate the external call
        System.out.println("  [ExternalAPI] Called " + providerName + " with: " + payload);
        return "response_from_" + providerName;
    }
}
