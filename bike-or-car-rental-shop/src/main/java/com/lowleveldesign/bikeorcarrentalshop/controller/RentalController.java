package com.lowleveldesign.bikeorcarrentalshop.controller;

import com.lowleveldesign.bikeorcarrentalshop.model.entity.Rental;
import com.lowleveldesign.bikeorcarrentalshop.service.RentalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    public Rental recordRental(@RequestParam Long productId, @RequestParam Long customerId, @RequestParam  int rentDuration) {
        return rentalService.recordRental(productId, customerId, rentDuration);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Rental> returnRental(@PathVariable Long id) {
        Rental updatedRental = rentalService.returnRental(id);
        return ResponseEntity.ok(updatedRental);
    }
}
