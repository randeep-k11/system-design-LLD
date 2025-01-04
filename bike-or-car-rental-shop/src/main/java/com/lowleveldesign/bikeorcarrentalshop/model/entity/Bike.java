package com.lowleveldesign.bikeorcarrentalshop.model.entity;

import com.lowleveldesign.bikeorcarrentalshop.model.enums.ProductType;
import com.lowleveldesign.bikeorcarrentalshop.model.enums.Size;
import jakarta.persistence.Entity;

@Entity
public class Bike extends Product {

    private Size size;

    public Bike(Size size, double rentalPricePerHour, double rentalPricePerDay) {
        super(ProductType.BIKE, rentalPricePerHour, rentalPricePerDay);
        this.size = size;
    }

    public Bike() {

    }

    public Size getSize() {
        return size;
    }
}
