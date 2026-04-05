package com.example.parking;

import java.util.Objects;

public class Vehicle {
    private final String licensePlate;
    private final VehicleType type;

    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = Objects.requireNonNull(licensePlate, "licensePlate must not be null");
        this.type = Objects.requireNonNull(type, "vehicleType must not be null");
    }

    public String getLicensePlate() { return licensePlate; }
    public VehicleType getType() { return type; }

    @Override
    public String toString() {
        return type + " [" + licensePlate + "]";
    }
}
