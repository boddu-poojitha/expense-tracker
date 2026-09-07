package com.personalfinance.expense_tracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.personalfinance.expense_tracker.dto.ExpenseDTO;
import com.personalfinance.expense_tracker.entity.Category;
import com.personalfinance.expense_tracker.entity.Expense;
import com.personalfinance.expense_tracker.entity.User;
import com.personalfinance.expense_tracker.exception.ResourceNotFoundException;
import com.personalfinance.expense_tracker.repository.CategoryRepository;
import com.personalfinance.expense_tracker.repository.ExpenseRepository;
import com.personalfinance.expense_tracker.repository.UserRepository;
import com.personalfinance.expense_tracker.service.impl.ExpenseServiceImpl;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceImplTest {

    @Mock
    private ExpenseRepository expenseRepo;

    @Mock
    private UserRepository userRepo;

    @Mock
    private CategoryRepository categoryRepo;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @Test
    void getExpenseById_shouldReturnExpense() {

        // Arrange
        long expenseId = 1;

        Expense expense = new Expense();
        expense.setExpenseId(expenseId);
        expense.setTitle("Lunch");
        expense.setAmount(250.0);
        expense.setExpenseDate(LocalDate.of(2026, 8, 26));
        expense.setNote("Lunch expense");
        expense.setPaymentMethod("Cash");

        when(expenseRepo.findById(expenseId))
                .thenReturn(Optional.of(expense));

        // Act
        ExpenseDTO result = expenseService.getExpenseById(expenseId);

        // Assert
        assertNotNull(result);
        assertEquals(expenseId, result.getExpenseId());
        assertEquals("Lunch", result.getTitle());
        assertEquals(250.0, result.getAmount());
        assertEquals(LocalDate.of(2026, 8, 26), result.getExpenseDate());
        assertEquals("Lunch expense", result.getNote());
        assertEquals("Cash", result.getPaymentMethod());

        verify(expenseRepo).findById(expenseId);
    }
    
    @Test
    void getExpenseById_shouldThrowException_whenExpenseNotFound() {

        // Arrange
        long expenseId = 999;

        when(expenseRepo.findById(expenseId))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception =
                org.junit.jupiter.api.Assertions.assertThrows(
                        ResourceNotFoundException.class,
                        () -> expenseService.getExpenseById(expenseId));

        assertEquals(
                "Expense not found with id: 999",
                exception.getMessage());

        verify(expenseRepo).findById(expenseId);
    }
    
    
    @Test
    void addExpense_shouldReturnSavedExpense() {

        // Arrange
        ExpenseDTO expenseDTO = new ExpenseDTO();

        expenseDTO.setTitle("Lunch");
        expenseDTO.setAmount(250.0);
        expenseDTO.setExpenseDate(LocalDate.of(2026, 8, 30));
        expenseDTO.setNote("Lunch expense");
        expenseDTO.setPaymentMethod("Cash");
        expenseDTO.setUserId(1);
        expenseDTO.setCategoryId(2);

        User user = new User();
        user.setUserId(1L);

        Category category = new Category();
        category.setCategoryId(2);

        Expense savedExpense = new Expense();
        savedExpense.setExpenseId(3);
        savedExpense.setTitle("Lunch");
        savedExpense.setAmount(250.0);
        savedExpense.setExpenseDate(LocalDate.of(2026, 8, 30));
        savedExpense.setNote("Lunch expense");
        savedExpense.setPaymentMethod("Cash");
        savedExpense.setUser(user);
        savedExpense.setCategory(category);

        when(userRepo.findById(1L))
                .thenReturn(Optional.of(user));

        when(categoryRepo.findById(2L))
                .thenReturn(Optional.of(category));

        when(expenseRepo.save(any(Expense.class)))
                .thenReturn(savedExpense);

        // Act
        ExpenseDTO result = expenseService.addExpense(expenseDTO);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.getExpenseId());
        assertEquals("Lunch", result.getTitle());
        assertEquals(250.0, result.getAmount());
        assertEquals("Cash", result.getPaymentMethod());
        assertEquals(1, result.getUserId());
        assertEquals(2, result.getCategoryId());

        verify(userRepo).findById(1L);
        verify(categoryRepo).findById(2L);
        verify(expenseRepo).save(any(Expense.class));
    }
    
    @Test
    void addExpense_shouldThrowException_whenUserNotFound() {

        // Arrange
        ExpenseDTO expenseDTO = new ExpenseDTO();

        expenseDTO.setUserId(999);
        expenseDTO.setCategoryId(2);

        when(userRepo.findById(999L))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception =
                assertThrows(
                        ResourceNotFoundException.class,
                        () -> expenseService.addExpense(expenseDTO)
                );

        assertEquals(
                "User not found with id: 999",
                exception.getMessage()
        );

        verify(userRepo).findById(999L);

        verify(categoryRepo, never()).findById(anyLong());

        verify(expenseRepo, never()).save(any(Expense.class));
    }
    
    
    @Test
    void addExpense_shouldThrowException_whenCategoryNotFound() {

        // Arrange
        ExpenseDTO expenseDTO = new ExpenseDTO();

        expenseDTO.setUserId(1);
        expenseDTO.setCategoryId(999);

        User user = new User();
        user.setUserId(1L);

        when(userRepo.findById(1L))
                .thenReturn(Optional.of(user));

        when(categoryRepo.findById(999L))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception =
                assertThrows(
                        ResourceNotFoundException.class,
                        () -> expenseService.addExpense(expenseDTO)
                );

        assertEquals(
                "Category not found with id: 999",
                exception.getMessage()
        );

        verify(userRepo).findById(1L);
        verify(categoryRepo).findById(999L);

        verify(expenseRepo, never()).save(any(Expense.class));
    }
   
}

