package com.lowleveldesign.bikeorcarrentalshop.repository;

import com.lowleveldesign.bikeorcarrentalshop.model.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
