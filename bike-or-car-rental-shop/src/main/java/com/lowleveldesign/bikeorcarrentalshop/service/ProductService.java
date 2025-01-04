package com.lowleveldesign.bikeorcarrentalshop.service;

import com.lowleveldesign.bikeorcarrentalshop.model.entity.Bike;
import com.lowleveldesign.bikeorcarrentalshop.model.entity.Product;
import com.lowleveldesign.bikeorcarrentalshop.model.enums.Size;
import com.lowleveldesign.bikeorcarrentalshop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAvailableProducts() {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.isAvailable())
                .toList();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void removeProduct(Long productId) {
        productRepository.deleteById(productId);
    }


    public List<Product> getProductCountBySize(Size size) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product instanceof Bike && ((Bike) product).getSize() == size)
                .filter(product -> product.isAvailable())
                .toList();
    }
}
