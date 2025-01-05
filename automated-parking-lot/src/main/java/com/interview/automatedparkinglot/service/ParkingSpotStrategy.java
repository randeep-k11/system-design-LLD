package com.interview.automatedparkinglot.service;

import com.interview.automatedparkinglot.model.entity.ParkingSpot;

import java.util.Optional;

public interface ParkingSpotStrategy {
    Optional<ParkingSpot> findSpot();
}
