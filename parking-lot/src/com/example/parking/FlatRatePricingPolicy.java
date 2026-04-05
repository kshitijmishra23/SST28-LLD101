package com.example.parking;

import java.util.Map;
import java.util.Objects;

/**
 * Simple flat-rate pricing: each slot type has a fixed per-hour charge.
 * Rates are injected via constructor so they're easy to change at startup.
 */
public class FlatRatePricingPolicy implements PricingPolicy {
    private final Map<SlotType, Integer> rates;

    public FlatRatePricingPolicy(Map<SlotType, Integer> rates) {
        this.rates = Objects.requireNonNull(rates, "rates map must not be null");
    }

    @Override
    public int ratePerHour(SlotType slotType) {
        Integer rate = rates.get(slotType);
        if (rate == null) {
            throw new IllegalArgumentException("No rate configured for slot type: " + slotType);
        }
        return rate;
    }
}
