package com.example.parking;

import java.util.List;
import java.util.Map;

/**
 * Maps each vehicle type to the slot types it can fit into (smallest first).
 * A two-wheeler fits in SMALL, MEDIUM, or LARGE; a car needs at least MEDIUM, etc.
 * Kept in one place so the mapping is easy to update without touching strategies.
 */
public final class VehicleSlotMapping {

    private static final Map<VehicleType, List<SlotType>> COMPATIBLE = Map.of(
        VehicleType.TWO_WHEELER, List.of(SlotType.SMALL, SlotType.MEDIUM, SlotType.LARGE),
        VehicleType.CAR,         List.of(SlotType.MEDIUM, SlotType.LARGE),
        VehicleType.BUS,         List.of(SlotType.LARGE)
    );

    private VehicleSlotMapping() { }

    public static List<SlotType> compatibleSlots(VehicleType vehicleType) {
        List<SlotType> slots = COMPATIBLE.get(vehicleType);
        if (slots == null) {
            throw new IllegalArgumentException("Unknown vehicle type: " + vehicleType);
        }
        return slots;
    }
}
