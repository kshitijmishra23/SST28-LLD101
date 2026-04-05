package com.example.parking;

import java.util.List;
import java.util.Optional;

/**
 * Strategy interface (Strategy Pattern) for selecting a parking slot.
 * New allocation policies can be plugged in without modifying ParkingLot — OCP.
 */
public interface SlotAssignmentStrategy {
    Optional<ParkingSlot> findSlot(List<ParkingSlot> slots,
                                   VehicleType vehicleType,
                                   SlotType requestedSlotType,
                                   Gate entryGate);
}
