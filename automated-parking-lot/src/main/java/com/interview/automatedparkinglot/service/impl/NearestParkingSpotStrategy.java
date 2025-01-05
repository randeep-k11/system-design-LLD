package com.interview.automatedparkinglot.service.impl;

import com.interview.automatedparkinglot.model.entity.ParkingSpot;
import com.interview.automatedparkinglot.repository.impl.ParkingSpotRepository;
import com.interview.automatedparkinglot.service.ParkingSpotStrategy;

import java.sql.SQLException;
import java.util.Optional;

public class NearestParkingSpotStrategy implements ParkingSpotStrategy {
    private ParkingSpotRepository parkingSpotRepository;

    public NearestParkingSpotStrategy(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Override
    public Optional<ParkingSpot> findSpot() {
        try {
            return Optional.ofNullable(parkingSpotRepository.findAvailableSpot());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding nearest available spot", e);
        }
    }
}