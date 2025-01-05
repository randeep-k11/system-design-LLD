package com.interview.automatedparkinglot.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private UUID ticketId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private String parkingSpotId;
    private Vehicle vehicle;
    private double parkingCharge;

    public Ticket(String parkingSpotId, Vehicle vehicle) {
        this.ticketId = UUID.randomUUID();
        this.entryTime = LocalDateTime.now();
        this.parkingSpotId = parkingSpotId;
        this.vehicle = vehicle;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public String getParkingSpotId() {
        return parkingSpotId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getParkingCharge() {
        return parkingCharge;
    }

    public void setParkingCharge(double parkingCharge) {
        this.parkingCharge = parkingCharge;
    }
}