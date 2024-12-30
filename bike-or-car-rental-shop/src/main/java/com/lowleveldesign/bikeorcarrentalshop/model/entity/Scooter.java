package com.lowleveldesign.bikeorcarrentalshop.model.entity;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Scooter extends Product {

    private String motorType;

    public Scooter(String motorType, double rentalPricePerHour, double rentalPricePerDay) {
        super("Scooter", rentalPricePerHour, rentalPricePerDay);
        this.motorType = motorType;
    }
}
