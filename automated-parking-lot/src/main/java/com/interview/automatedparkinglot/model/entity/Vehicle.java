package com.interview.automatedparkinglot.model.entity;

import com.interview.automatedparkinglot.model.enums.VehicleType;

import java.util.UUID;

public class Vehicle {
    private UUID vehicleId;
    private String licensePlateNumber;
    private VehicleType vehicleType;

    public Vehicle(String licensePlateNumber, VehicleType vehicleType) {
        this.vehicleId = UUID.randomUUID();
        this.licensePlateNumber = licensePlateNumber;
        this.vehicleType = vehicleType;
    }

    public UUID getVehicleId() {
        return vehicleId;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}