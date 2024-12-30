package com.lowleveldesign.bikeorcarrentalshop.repository;

import com.lowleveldesign.bikeorcarrentalshop.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
