package com.personalfinance.expense_tracker.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.personalfinance.expense_tracker.entity.Category;
import com.personalfinance.expense_tracker.repository.CategoryRepository;
import com.personalfinance.expense_tracker.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService{
	
	private final CategoryRepository categoryRepo;
	public CategoryServiceImpl(CategoryRepository categoryRepo) {
		this.categoryRepo=categoryRepo;
	}
	
	@Override
	public Category addCategory(Category category) {
		return categoryRepo.save(category);
	}
	@Override
	public List<Category> getAllCategories() {
		return categoryRepo.findAll();
	}
	@Override
	public Category getCategoryById(long categoryId) {
		return categoryRepo.findById(categoryId).orElse(null);
	}
	@Override
	public Category updateCategory(Category category) {
		return categoryRepo.save(category);
	}
	@Override
	public void deleteCategory(long categoryId) {
		 categoryRepo.deleteById(categoryId);
	}

	
	
}
