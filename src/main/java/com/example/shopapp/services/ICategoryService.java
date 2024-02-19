package com.example.shopapp.services;

import com.example.shopapp.dtos.CategoryDTO;
import com.example.shopapp.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoryService {
    Category getCategoryById(Long id);

    Category createCategory(CategoryDTO categoryDTO);

    List<Category> getAllCategories();
}
