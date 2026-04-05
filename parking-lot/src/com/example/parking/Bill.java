package com.example.parking;

import java.time.LocalDateTime;

public class Bill {
    private final ParkingTicket ticket;
    private final LocalDateTime exitTime;
    private final long hoursParked;
    private final int totalAmount;

    public Bill(ParkingTicket ticket, LocalDateTime exitTime, long hoursParked, int totalAmount) {
        this.ticket      = ticket;
        this.exitTime    = exitTime;
        this.hoursParked = hoursParked;
        this.totalAmount = totalAmount;
    }

    public ParkingTicket getTicket()    { return ticket; }
    public LocalDateTime getExitTime()  { return exitTime; }
    public long getHoursParked()        { return hoursParked; }
    public int getTotalAmount()         { return totalAmount; }

    @Override
    public String toString() {
        return "Bill{hours=" + hoursParked + ", amount=Rs." + totalAmount
             + ", slot=" + ticket.getSlot().getType() + "}";
    }
}
