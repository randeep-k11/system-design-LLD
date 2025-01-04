package com.lowleveldesign.bikeorcarrentalshop.controller;

import com.lowleveldesign.bikeorcarrentalshop.model.entity.Product;
import com.lowleveldesign.bikeorcarrentalshop.model.enums.Size;
import com.lowleveldesign.bikeorcarrentalshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/products")
@AllArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/available")
    public List<Product> getAvailableProducts() {
        return productService.getAvailableProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("/{productId}")
    public void removeProduct(@PathVariable Long productId) {
        productService.removeProduct(productId);
    }

    @GetMapping("/count")
    public List<Product> getProductCountBySize(@RequestParam Size size) {
        return productService.getProductCountBySize(size);
    }

}
