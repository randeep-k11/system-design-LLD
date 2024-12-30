package com.lowleveldesign.bikeorcarrentalshop.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Long customerId;
    private Date rentalStartDate;
    private Date expectedReturnDate;
    private Date actualReturnDate;
    private boolean isOverdue;

    public Rental(Long productId, Long customerId, Date rentalStartDate, Date expectedReturnDate) {
        this.productId = productId;
        this.customerId = customerId;
        this.rentalStartDate = rentalStartDate;
        this.expectedReturnDate = expectedReturnDate;
        this.isOverdue = false;
    }
}
