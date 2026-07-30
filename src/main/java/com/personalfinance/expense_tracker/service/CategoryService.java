package com.personalfinance.expense_tracker.service;

import java.util.List;

import com.personalfinance.expense_tracker.entity.Category;

public interface CategoryService {


	Category addCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(long categoryId);

    Category updateCategory(Category category);

    void deleteCategory(long categoryId);
}
