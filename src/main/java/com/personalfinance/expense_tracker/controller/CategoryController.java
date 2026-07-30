package com.personalfinance.expense_tracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personalfinance.expense_tracker.entity.Category;
import com.personalfinance.expense_tracker.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	
	 private final CategoryService categoryService;

	    public CategoryController(CategoryService categoryService) {
	        this.categoryService = categoryService;
	    }

	    @PostMapping
	    public Category addCategory(@RequestBody Category category) {
	        return categoryService.addCategory(category);
	    }

	    @GetMapping
	    public List<Category> getAllCategories() {
	        return categoryService.getAllCategories();
	    }

	    @GetMapping("/{id}")
	    public Category getCategoryById(@PathVariable long id) {
	        return categoryService.getCategoryById(id);
	    }

	    @PutMapping
	    public Category updateCategory(@RequestBody Category category) {
	        return categoryService.updateCategory(category);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteCategory(@PathVariable long id) {
	        categoryService.deleteCategory(id);
	    }

}
