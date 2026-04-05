package com.example.parking;

/**
 * Abstracts how we calculate the hourly rate for a given slot type.
 * Different policies (flat rate, surge pricing, weekend discounts)
 * can implement this without touching the billing logic — OCP / DIP.
 */
public interface PricingPolicy {
    int ratePerHour(SlotType slotType);
}
