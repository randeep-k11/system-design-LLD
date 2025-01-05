package com.interview.automatedparkinglot.service;

import com.interview.automatedparkinglot.model.enums.VehicleType;

import java.time.LocalDateTime;

public interface IChargesService {
    double calculateCharges(VehicleType vehicleType, LocalDateTime entryTime, LocalDateTime exitTime);
}