package com.example.parking;

import java.time.LocalDateTime;
import java.util.Objects;

public class ParkingTicket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final ParkingSlot slot;
    private final LocalDateTime entryTime;

    public ParkingTicket(String ticketId, Vehicle vehicle, ParkingSlot slot, LocalDateTime entryTime) {
        this.ticketId  = Objects.requireNonNull(ticketId);
        this.vehicle   = Objects.requireNonNull(vehicle);
        this.slot      = Objects.requireNonNull(slot);
        this.entryTime = Objects.requireNonNull(entryTime);
    }

    public String getTicketId()        { return ticketId; }
    public Vehicle getVehicle()        { return vehicle; }
    public ParkingSlot getSlot()       { return slot; }
    public LocalDateTime getEntryTime(){ return entryTime; }

    @Override
    public String toString() {
        return "Ticket{" + ticketId + ", vehicle=" + vehicle + ", slot=" + slot + "}";
    }
}
