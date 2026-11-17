package com.scaler.productservice.dto;

import com.scaler.productservice.models.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// INFO This class is used to map the fields for the CLIENT Side
@Getter
@Setter
public class CategoryDto extends BaseModel {
    private String description;
    private List<ProductDto> productDtoList;
}
