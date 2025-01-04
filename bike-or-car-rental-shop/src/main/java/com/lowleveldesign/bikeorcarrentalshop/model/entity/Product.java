package com.lowleveldesign.bikeorcarrentalshop.model.entity;

import com.lowleveldesign.bikeorcarrentalshop.model.enums.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ProductType type;
    private boolean available;
    private double rentalPricePerHour;
    private double rentalPricePerDay;
    private Date dateAdded;

    public Product() {
    }

    public Product(ProductType type, double rentalPricePerHour, double rentalPricePerDay) {
        this.type = type;
        this.rentalPricePerHour = rentalPricePerHour;
        this.rentalPricePerDay = rentalPricePerDay;
        this.dateAdded = new Date();
    }


    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getRentalPricePerHour() {
        return rentalPricePerHour;
    }

    public void setRentalPricePerHour(double rentalPricePerHour) {
        this.rentalPricePerHour = rentalPricePerHour;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(double rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
