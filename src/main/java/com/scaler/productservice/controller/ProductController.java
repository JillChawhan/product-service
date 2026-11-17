package com.scaler.productservice.controller;

import com.scaler.productservice.dto.CategoryDto;
import com.scaler.productservice.dto.ProductDto;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// Bean means a SINGLETON instance of a class created by SPRINGBOOT CONTEXT
// All the beans are processed by SPRINGBOOT CONTEXT and are available for dependency injection
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        Product product = productService.getProductDetailsById(id);

        ProductDto productDto = from(product);
        return new ResponseEntity<ProductDto>(productDto, HttpStatus.OK);
    }

    // INFO() MAPPERS

    // 1. ProductDto from Product

    private ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setId(product.getId());
        productDto.setImageUrl(product.getImageUrl());
        if (product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setDescription(product.getCategory().getDescription());
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            productDto.setCategoryDto(categoryDto);
        }
        return productDto;
    }
}
