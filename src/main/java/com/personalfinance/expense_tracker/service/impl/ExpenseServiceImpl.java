package com.personalfinance.expense_tracker.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.personalfinance.expense_tracker.dto.ExpenseDTO;
import com.personalfinance.expense_tracker.entity.Category;
import com.personalfinance.expense_tracker.entity.Expense;
import com.personalfinance.expense_tracker.entity.User;
import com.personalfinance.expense_tracker.exception.ResourceNotFoundException;
import com.personalfinance.expense_tracker.mapper.ExpenseMapper;
import com.personalfinance.expense_tracker.repository.CategoryRepository;
import com.personalfinance.expense_tracker.repository.ExpenseRepository;
import com.personalfinance.expense_tracker.repository.UserRepository;
import com.personalfinance.expense_tracker.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepo;
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;

    public ExpenseServiceImpl(
            ExpenseRepository expenseRepo,
            UserRepository userRepo,
            CategoryRepository categoryRepo) {

        this.expenseRepo = expenseRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public ExpenseDTO addExpense(ExpenseDTO expenseDTO) {

        User user = userRepo.findById(expenseDTO.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + expenseDTO.getUserId()));

        Category category = categoryRepo.findById(expenseDTO.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id: " + expenseDTO.getCategoryId()));

        Expense expense = ExpenseMapper.toEntity(expenseDTO);

        expense.setUser(user);
        expense.setCategory(category);

        Expense savedExpense = expenseRepo.save(expense);

        return ExpenseMapper.toDTO(savedExpense);
    }

    @Override
    public List<ExpenseDTO> getAllExpenses() {

        return expenseRepo.findAll()
                .stream()
                .map(ExpenseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDTO getExpenseById(long expenseId) {

        Expense expense = expenseRepo.findById(expenseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Expense not found with id: " + expenseId));

        return ExpenseMapper.toDTO(expense);
    }

    @Override
    public ExpenseDTO updateExpense(ExpenseDTO expenseDTO) {

        Expense existingExpense = expenseRepo.findById(expenseDTO.getExpenseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Expense not found with id: " + expenseDTO.getExpenseId()));

        User user = userRepo.findById(expenseDTO.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + expenseDTO.getUserId()));

        Category category = categoryRepo.findById(expenseDTO.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id: " + expenseDTO.getCategoryId()));

        existingExpense.setTitle(expenseDTO.getTitle());
        existingExpense.setAmount(expenseDTO.getAmount());
        existingExpense.setExpenseDate(expenseDTO.getExpenseDate());
        existingExpense.setNote(expenseDTO.getNote());
        existingExpense.setPaymentMethod(expenseDTO.getPaymentMethod());
        existingExpense.setUser(user);
        existingExpense.setCategory(category);

        Expense updatedExpense = expenseRepo.save(existingExpense);

        return ExpenseMapper.toDTO(updatedExpense);
    }

    @Override
    public void deleteExpense(long expenseId) {

        Expense expense = expenseRepo.findById(expenseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Expense not found with id: " + expenseId));

        expenseRepo.delete(expense);
    }
}