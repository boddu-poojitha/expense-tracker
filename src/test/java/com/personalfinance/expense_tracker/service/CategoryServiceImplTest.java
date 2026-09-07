package com.personalfinance.expense_tracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.personalfinance.expense_tracker.dto.CategoryDTO;
import com.personalfinance.expense_tracker.entity.Category;
import com.personalfinance.expense_tracker.exception.ResourceNotFoundException;
import com.personalfinance.expense_tracker.repository.CategoryRepository;
import com.personalfinance.expense_tracker.service.impl.CategoryServiceImpl;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepo;

    @InjectMocks
    private CategoryServiceImpl categoryService;


    @Test
    void addCategory_shouldReturnSavedCategory() {

        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setCategoryName("Food");
        categoryDTO.setDescription("Food related expenses");

        Category savedCategory = new Category();

        savedCategory.setCategoryId(1L);
        savedCategory.setCategoryName("Food");
        savedCategory.setDescription("Food related expenses");

        when(categoryRepo.save(any(Category.class)))
                .thenReturn(savedCategory);

        // Act
        CategoryDTO result = categoryService.addCategory(categoryDTO);

        // Assert
        assertNotNull(result);

        assertEquals(1L, result.getCategoryId());
        assertEquals("Food", result.getCategoryName());
        assertEquals(
                "Food related expenses",
                result.getDescription()
        );

        verify(categoryRepo).save(any(Category.class));
    }


    @Test
    void getAllCategories_shouldReturnAllCategories() {

        // Arrange
        Category category1 = new Category();

        category1.setCategoryId(1L);
        category1.setCategoryName("Food");
        category1.setDescription("Food expenses");

        Category category2 = new Category();

        category2.setCategoryId(2L);
        category2.setCategoryName("Travel");
        category2.setDescription("Travel expenses");

        when(categoryRepo.findAll())
                .thenReturn(List.of(category1, category2));

        // Act
        List<CategoryDTO> result =
                categoryService.getAllCategories();

        // Assert
        assertNotNull(result);

        assertEquals(2, result.size());

        assertEquals(1L, result.get(0).getCategoryId());
        assertEquals("Food", result.get(0).getCategoryName());
        assertEquals("Food expenses",
                result.get(0).getDescription());

        assertEquals(2L, result.get(1).getCategoryId());
        assertEquals("Travel", result.get(1).getCategoryName());
        assertEquals("Travel expenses",
                result.get(1).getDescription());

        verify(categoryRepo).findAll();
    }


    @Test
    void getCategoryById_shouldReturnCategory() {

        // Arrange
        long categoryId = 1L;

        Category category = new Category();

        category.setCategoryId(categoryId);
        category.setCategoryName("Food");
        category.setDescription("Food expenses");

        when(categoryRepo.findById(categoryId))
                .thenReturn(Optional.of(category));

        // Act
        CategoryDTO result =
                categoryService.getCategoryById(categoryId);

        // Assert
        assertNotNull(result);

        assertEquals(1L, result.getCategoryId());
        assertEquals("Food", result.getCategoryName());
        assertEquals(
                "Food expenses",
                result.getDescription()
        );

        verify(categoryRepo).findById(categoryId);
    }


    @Test
    void getCategoryById_shouldThrowException_whenCategoryNotFound() {

        // Arrange
        long categoryId = 999L;

        when(categoryRepo.findById(categoryId))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception =
                assertThrows(
                        ResourceNotFoundException.class,
                        () -> categoryService.getCategoryById(categoryId)
                );

        assertEquals(
                "Category not found with id: 999",
                exception.getMessage()
        );

        verify(categoryRepo).findById(categoryId);
    }


    @Test
    void updateCategory_shouldReturnUpdatedCategory() {

        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setCategoryId(1L);
        categoryDTO.setCategoryName("Food Updated");
        categoryDTO.setDescription("Updated food expenses");

        Category existingCategory = new Category();

        existingCategory.setCategoryId(1L);
        existingCategory.setCategoryName("Food");
        existingCategory.setDescription("Food expenses");

        Category updatedCategory = new Category();

        updatedCategory.setCategoryId(1L);
        updatedCategory.setCategoryName("Food Updated");
        updatedCategory.setDescription("Updated food expenses");

        when(categoryRepo.findById(1L))
                .thenReturn(Optional.of(existingCategory));

        when(categoryRepo.save(any(Category.class)))
                .thenReturn(updatedCategory);

        // Act
        CategoryDTO result =
                categoryService.updateCategory(categoryDTO);

        // Assert
        assertNotNull(result);

        assertEquals(1L, result.getCategoryId());
        assertEquals(
                "Food Updated",
                result.getCategoryName()
        );
        assertEquals(
                "Updated food expenses",
                result.getDescription()
        );

        verify(categoryRepo).findById(1L);
        verify(categoryRepo).save(any(Category.class));
    }


    @Test
    void updateCategory_shouldThrowException_whenCategoryNotFound() {

        // Arrange
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setCategoryId(999L);
        categoryDTO.setCategoryName("Food");
        categoryDTO.setDescription("Food expenses");

        when(categoryRepo.findById(999L))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception =
                assertThrows(
                        ResourceNotFoundException.class,
                        () -> categoryService.updateCategory(categoryDTO)
                );

        assertEquals(
                "Category not found with id: 999",
                exception.getMessage()
        );

        verify(categoryRepo).findById(999L);

        verify(categoryRepo, never())
                .save(any(Category.class));
    }


    @Test
    void deleteCategory_shouldDeleteCategory() {

        // Arrange
        long categoryId = 1L;

        Category category = new Category();

        category.setCategoryId(categoryId);
        category.setCategoryName("Food");
        category.setDescription("Food expenses");

        when(categoryRepo.findById(categoryId))
                .thenReturn(Optional.of(category));

        // Act
        categoryService.deleteCategory(categoryId);

        // Assert
        verify(categoryRepo).findById(categoryId);
        verify(categoryRepo).delete(category);
    }


    @Test
    void deleteCategory_shouldThrowException_whenCategoryNotFound() {

        // Arrange
        long categoryId = 999L;

        when(categoryRepo.findById(categoryId))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception =
                assertThrows(
                        ResourceNotFoundException.class,
                        () -> categoryService.deleteCategory(categoryId)
                );

        assertEquals(
                "Category not found with id: 999",
                exception.getMessage()
        );

        verify(categoryRepo).findById(categoryId);

        verify(categoryRepo, never())
                .delete(any(Category.class));
    }
}