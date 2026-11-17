package com.scaler.productservice.service;

import com.scaler.productservice.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductDetailsById(Long id);

    List<Product> getAllProducts();

    void deleteProductById(Long id);

    Product createProduct(Product product);

    Product replaceProduct(Long id, Product inputProduct);
}
