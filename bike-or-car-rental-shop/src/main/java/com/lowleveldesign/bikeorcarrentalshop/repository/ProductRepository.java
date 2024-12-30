package com.lowleveldesign.bikeorcarrentalshop.repository;

import com.lowleveldesign.bikeorcarrentalshop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
