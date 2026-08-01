package com.personalfinance.expense_tracker.service;

import java.util.List;

import com.personalfinance.expense_tracker.dto.CategoryDTO;

public interface CategoryService {

    CategoryDTO addCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO);

    void deleteCategory(long categoryId);
}