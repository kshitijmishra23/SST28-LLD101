package com.example.parking;

public class ParkingSlot {
    private final int slotNumber;
    private final SlotType type;
    private final int floor;
    private boolean occupied;

    public ParkingSlot(int slotNumber, SlotType type, int floor) {
        this.slotNumber = slotNumber;
        this.type = type;
        this.floor = floor;
        this.occupied = false;
    }

    public int getSlotNumber() { return slotNumber; }
    public SlotType getType()  { return type; }
    public int getFloor()      { return floor; }
    public boolean isOccupied(){ return occupied; }

    public void occupy() { this.occupied = true; }
    public void free()   { this.occupied = false; }

    @Override
    public String toString() {
        return "Slot-" + slotNumber + "(" + type + ", floor=" + floor + ")";
    }
}
