package com.scaler.productservice.dto;

import lombok.Getter;
import lombok.Setter;

// INFO This class is used to map the fields for the CLIENT Side
@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private CategoryDto categoryDto;
}
