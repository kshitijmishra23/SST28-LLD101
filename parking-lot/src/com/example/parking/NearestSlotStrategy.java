package com.example.parking;

import java.util.List;
import java.util.Optional;

/**
 * Picks the closest available compatible slot relative to the entry gate's floor,
 * breaking ties by lower slot number. Respects the requested slot type while
 * also allowing overflow into larger compatible slots when needed.
 */
public class NearestSlotStrategy implements SlotAssignmentStrategy {

    @Override
    public Optional<ParkingSlot> findSlot(List<ParkingSlot> slots,
                                           VehicleType vehicleType,
                                           SlotType requestedSlotType,
                                           Gate entryGate) {

        List<SlotType> allowed = VehicleSlotMapping.compatibleSlots(vehicleType);

        // first try the exact requested type
        Optional<ParkingSlot> exact = slots.stream()
                .filter(s -> !s.isOccupied())
                .filter(s -> s.getType() == requestedSlotType)
                .min((a, b) -> compareByDistance(a, b, entryGate));

        if (exact.isPresent()) return exact;

        // fallback: any compatible slot, nearest first
        return slots.stream()
                .filter(s -> !s.isOccupied())
                .filter(s -> allowed.contains(s.getType()))
                .min((a, b) -> compareByDistance(a, b, entryGate));
    }

    private int compareByDistance(ParkingSlot a, ParkingSlot b, Gate gate) {
        int distA = Math.abs(a.getFloor() - gate.getFloor()) * 1000 + a.getSlotNumber();
        int distB = Math.abs(b.getFloor() - gate.getFloor()) * 1000 + b.getSlotNumber();
        return Integer.compare(distA, distB);
    }
}
