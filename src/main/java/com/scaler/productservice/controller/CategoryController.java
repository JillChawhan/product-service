package com.scaler.productservice.controller;

import com.scaler.productservice.models.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Electronics");
        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        return categories;
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        Category c2 = new Category();
        c2.setId(id);
        return c2;
    }
}
