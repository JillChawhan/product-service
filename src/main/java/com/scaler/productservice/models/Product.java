package com.scaler.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private String description;
    private String imageUrl;
    private Category category;
}
