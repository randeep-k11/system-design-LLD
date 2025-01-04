package com.lowleveldesign.bikeorcarrentalshop.service;

import com.lowleveldesign.bikeorcarrentalshop.model.entity.Customer;
import com.lowleveldesign.bikeorcarrentalshop.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerByCustomerId(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }
}
