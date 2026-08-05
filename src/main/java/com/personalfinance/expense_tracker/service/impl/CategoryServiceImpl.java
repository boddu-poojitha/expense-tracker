package com.personalfinance.expense_tracker.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.personalfinance.expense_tracker.dto.CategoryDTO;
import com.personalfinance.expense_tracker.entity.Category;
import com.personalfinance.expense_tracker.exception.ResourceNotFoundException;
import com.personalfinance.expense_tracker.mapper.CategoryMapper;
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

        Category category = CategoryMapper.toEntity(categoryDTO);

        Category savedCategory = categoryRepo.save(category);

        return CategoryMapper.toDTO(savedCategory);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {

        return categoryRepo.findAll()
                .stream()
                .map(CategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(long categoryId) {

    	Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

    	return CategoryMapper.toDTO(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {

        Category category = categoryRepo.findById(categoryDTO.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id: "
                                        + categoryDTO.getCategoryId()));

        category.setCategoryName(categoryDTO.getCategoryName());
        category.setDescription(categoryDTO.getDescription());

        Category updatedCategory = categoryRepo.save(category);

        return CategoryMapper.toDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(long categoryId) {

        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id: " + categoryId));

        categoryRepo.delete(category);
    }
}