package com.example.parking;

public class Gate {
    private final int gateNumber;
    private final int floor;

    public Gate(int gateNumber, int floor) {
        this.gateNumber = gateNumber;
        this.floor = floor;
    }

    public int getGateNumber() { return gateNumber; }
    public int getFloor()      { return floor; }

    @Override
    public String toString() {
        return "Gate-" + gateNumber + "(floor=" + floor + ")";
    }
}
