package com.lowleveldesign.bikeorcarrentalshop.model.entity;

import com.lowleveldesign.bikeorcarrentalshop.model.enums.MotorStyle;
import com.lowleveldesign.bikeorcarrentalshop.model.enums.ProductType;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Scooter extends Product {

    private MotorStyle motorStyle;

    public Scooter(MotorStyle motorStyle, double rentalPricePerHour, double rentalPricePerDay) {
        super(ProductType.SCOOTER, rentalPricePerHour, rentalPricePerDay);
        this.motorStyle = motorStyle;
    }
}
