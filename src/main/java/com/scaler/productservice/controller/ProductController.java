package com.scaler.productservice.controller;

import com.scaler.productservice.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// Bean means a SINGLETON instance of a class created by SPRINGBOOT CONTEXT
// All the beans are processed by SPRINGBOOT CONTEXT and are available for dependency injection
@RestController
public class ProductController {
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("iPhone 18 Pro");
        p1.setDescription("8GB RAM, 1TB Storage");

        List<Product> products = new ArrayList<>();
        products.add(p1);
        return products;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        Product p2 = new Product();
        p2.setId(id);
        return p2;
    }
}
