package com.personalfinance.expense_tracker.mapper;

import com.personalfinance.expense_tracker.dto.CategoryDTO;
import com.personalfinance.expense_tracker.entity.Category;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {

        if (category == null) {
            return null;
        }

        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setCategoryName(category.getCategoryName());
        dto.setDescription(category.getDescription());

        return dto;
    }

    public static Category toEntity(CategoryDTO dto) {

        if (dto == null) {
            return null;
        }

        Category category = new Category();

        category.setCategoryId(dto.getCategoryId());
        category.setCategoryName(dto.getCategoryName());
        category.setDescription(dto.getDescription());

        return category;
    }
}