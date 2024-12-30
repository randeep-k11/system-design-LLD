package com.lowleveldesign.bikeorcarrentalshop.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private boolean isAvailable;
    private double rentalPricePerHour;
    private double rentalPricePerDay;

    public Product(String type, double rentalPricePerHour, double rentalPricePerDay) {
        this.type = type;
        this.rentalPricePerHour = rentalPricePerHour;
        this.rentalPricePerDay = rentalPricePerDay;
    }
}
