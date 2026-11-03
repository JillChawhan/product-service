package com.scaler.productservice.service;

import com.scaler.productservice.models.Product;

public interface IProductService {
    public Product getProductDetailsById(Long id);
}
