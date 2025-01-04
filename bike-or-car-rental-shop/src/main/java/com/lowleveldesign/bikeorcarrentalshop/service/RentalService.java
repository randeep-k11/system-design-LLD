package com.lowleveldesign.bikeorcarrentalshop.service;

import com.lowleveldesign.bikeorcarrentalshop.model.entity.Rental;
import com.lowleveldesign.bikeorcarrentalshop.repository.ProductRepository;
import com.lowleveldesign.bikeorcarrentalshop.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final ProductRepository productRepository;

    public RentalService(RentalRepository rentalRepository, ProductRepository productRepository) {
        this.rentalRepository = rentalRepository;
        this.productRepository = productRepository;
    }

    public Rental recordRental(Long productId, Long customerId, int rentDuration) {
        var product = productRepository.findById(productId).orElseThrow();
        product.setAvailable(false);
        productRepository.save(product);

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + rentDuration * 24 * 60 * 60 * 1000L);
        Rental rental = new Rental(productId, customerId, startDate, endDate);
        return rentalRepository.save(rental);
    }

    public Rental returnRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        rental.setActualReturnDate(new Date());
        rental.setOverdue(rental.getExpectedReturnDate().before(new Date()));
        rentalRepository.save(rental);
        return rental;
    }
}
