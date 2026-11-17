package com.scaler.productservice.repository;

import com.scaler.productservice.models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testLazyLoadingWithoutCategory() {
        Optional<Category> categoryOptional = categoryRepository.findById(1L);
        Category category = categoryOptional.get();
        System.out.printf("Category Name: " +category.getName());
    }

}