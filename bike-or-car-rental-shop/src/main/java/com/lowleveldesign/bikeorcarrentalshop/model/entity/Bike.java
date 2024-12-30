package com.lowleveldesign.bikeorcarrentalshop.model.entity;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Bike extends Product {

    private String size;

    public Bike(String size, double rentalPricePerHour, double rentalPricePerDay) {
        super("Bike", rentalPricePerHour, rentalPricePerDay);
        this.size = size;
    }
}
