package com.example.parking;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Generates a Bill when a vehicle exits.
 * Depends on PricingPolicy (DIP) so the billing logic never changes
 * even if pricing rules change.
 */
public class BillingService {
    private final PricingPolicy pricingPolicy;

    public BillingService(PricingPolicy pricingPolicy) {
        this.pricingPolicy = Objects.requireNonNull(pricingPolicy);
    }

    public Bill generateBill(ParkingTicket ticket, LocalDateTime exitTime) {
        long minutes = Duration.between(ticket.getEntryTime(), exitTime).toMinutes();
        long hours = (minutes + 59) / 60;   // round up — even 1 min counts as 1 hour
        if (hours <= 0) hours = 1;

        int rate = pricingPolicy.ratePerHour(ticket.getSlot().getType());
        int total = (int) (hours * rate);

        return new Bill(ticket, exitTime, hours, total);
    }
}
