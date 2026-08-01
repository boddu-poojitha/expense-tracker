package com.personalfinance.expense_tracker.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.personalfinance.expense_tracker.dto.CategoryDTO;
import com.personalfinance.expense_tracker.entity.Category;
import com.personalfinance.expense_tracker.exception.ResourceNotFoundException;
import com.personalfinance.expense_tracker.repository.CategoryRepository;
import com.personalfinance.expense_tracker.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepo;

    public CategoryServiceImpl(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {

        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setDescription(categoryDTO.getDescription());

        Category savedCategory = categoryRepo.save(category);

        CategoryDTO response = new CategoryDTO();
        response.setCategoryId(savedCategory.getCategoryId());
        response.setCategoryName(savedCategory.getCategoryName());
        response.setDescription(savedCategory.getDescription());

        return response;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        List<Category> categories = categoryRepo.findAll();

        return categories.stream().map(category -> {

            CategoryDTO dto = new CategoryDTO();
            dto.setCategoryId(category.getCategoryId());
            dto.setCategoryName(category.getCategoryName());
            dto.setDescription(category.getDescription());

            return dto;

        }).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(long categoryId) {

    	Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        if (category == null) {
            return null;
        }

        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setCategoryName(category.getCategoryName());
        dto.setDescription(category.getDescription());

        return dto;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {

        Category category = new Category();
        category.setCategoryId(categoryDTO.getCategoryId());
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setDescription(categoryDTO.getDescription());

        Category updatedCategory = categoryRepo.save(category);

        CategoryDTO response = new CategoryDTO();
        response.setCategoryId(updatedCategory.getCategoryId());
        response.setCategoryName(updatedCategory.getCategoryName());
        response.setDescription(updatedCategory.getDescription());

        return response;
    }

    @Override
    public void deleteCategory(long categoryId) {
        categoryRepo.deleteById(categoryId);
    }
}