package com.interview.automatedparkinglot.model.entity;

import com.interview.automatedparkinglot.model.enums.ParkingSlotStatus;

public class ParkingSpot {
    private String spotId;
    private ParkingSlotStatus status;
    private Vehicle parkedVehicle;

    public ParkingSpot(String spotId, ParkingSlotStatus status) {
        this.spotId = spotId;
        this.status = status;
    }

    public String getSpotId() {
        return spotId;
    }

    public ParkingSlotStatus getStatus() {
        return status;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.status = ParkingSlotStatus.OCCUPIED;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
        this.status = ParkingSlotStatus.AVAILABLE;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }
}