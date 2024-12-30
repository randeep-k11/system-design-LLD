package com.lowleveldesign.bikeorcarrentalshop.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactDetails;
    private double balance;

    public Customer(String name, String contactDetails, double balance) {
        this.name = name;
        this.contactDetails = contactDetails;
        this.balance = balance;
    }
}