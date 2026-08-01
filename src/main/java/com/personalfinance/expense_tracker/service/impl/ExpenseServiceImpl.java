package com.personalfinance.expense_tracker.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.personalfinance.expense_tracker.dto.ExpenseDTO;
import com.personalfinance.expense_tracker.entity.Category;
import com.personalfinance.expense_tracker.entity.Expense;
import com.personalfinance.expense_tracker.entity.User;
import com.personalfinance.expense_tracker.exception.ResourceNotFoundException;
import com.personalfinance.expense_tracker.repository.CategoryRepository;
import com.personalfinance.expense_tracker.repository.ExpenseRepository;
import com.personalfinance.expense_tracker.repository.UserRepository;
import com.personalfinance.expense_tracker.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepo;
    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;

    public ExpenseServiceImpl(ExpenseRepository expenseRepo,
                              UserRepository userRepo,
                              CategoryRepository categoryRepo) {
        this.expenseRepo = expenseRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public ExpenseDTO addExpense(ExpenseDTO expenseDTO) {

        User user = userRepo.findById(expenseDTO.getUserId()).orElse(null);
        Category category = categoryRepo.findById(expenseDTO.getCategoryId()).orElse(null);

        Expense expense = new Expense();
        expense.setTitle(expenseDTO.getTitle());
        expense.setAmount(expenseDTO.getAmount());
        expense.setExpenseDate(expenseDTO.getExpenseDate());
        expense.setNote(expenseDTO.getNote());
        expense.setPaymentMethod(expenseDTO.getPaymentMethod());
        expense.setUser(user);
        expense.setCategory(category);

        Expense savedExpense = expenseRepo.save(expense);

        ExpenseDTO response = new ExpenseDTO();
        response.setExpenseId(savedExpense.getExpenseId());
        response.setTitle(savedExpense.getTitle());
        response.setAmount(savedExpense.getAmount());
        response.setExpenseDate(savedExpense.getExpenseDate());
        response.setNote(savedExpense.getNote());
        response.setPaymentMethod(savedExpense.getPaymentMethod());
        response.setUserId(savedExpense.getUser().getUserId());
        response.setCategoryId(savedExpense.getCategory().getCategoryId());

        return response;
    }

    @Override
    public List<ExpenseDTO> getAllExpenses() {

        return expenseRepo.findAll().stream().map(expense -> {

            ExpenseDTO dto = new ExpenseDTO();
            dto.setExpenseId(expense.getExpenseId());
            dto.setTitle(expense.getTitle());
            dto.setAmount(expense.getAmount());
            dto.setExpenseDate(expense.getExpenseDate());
            dto.setNote(expense.getNote());
            dto.setPaymentMethod(expense.getPaymentMethod());
            dto.setUserId(expense.getUser().getUserId());
            dto.setCategoryId(expense.getCategory().getCategoryId());

            return dto;

        }).collect(Collectors.toList());
    }

    @Override
    public ExpenseDTO getExpenseById(long expenseId) {

        Expense expense = expenseRepo.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException("Expense not found"));

        if (expense == null) {
            return null;
        }

        ExpenseDTO dto = new ExpenseDTO();
        dto.setExpenseId(expense.getExpenseId());
        dto.setTitle(expense.getTitle());
        dto.setAmount(expense.getAmount());
        dto.setExpenseDate(expense.getExpenseDate());
        dto.setNote(expense.getNote());
        dto.setPaymentMethod(expense.getPaymentMethod());
        dto.setUserId(expense.getUser().getUserId());
        dto.setCategoryId(expense.getCategory().getCategoryId());

        return dto;
    }

    @Override
    public ExpenseDTO updateExpense(ExpenseDTO expenseDTO) {

        User user = userRepo.findById(expenseDTO.getUserId()).orElse(null);
        Category category = categoryRepo.findById(expenseDTO.getCategoryId()).orElse(null);

        Expense expense = new Expense();
        expense.setExpenseId(expenseDTO.getExpenseId());
        expense.setTitle(expenseDTO.getTitle());
        expense.setAmount(expenseDTO.getAmount());
        expense.setExpenseDate(expenseDTO.getExpenseDate());
        expense.setNote(expenseDTO.getNote());
        expense.setPaymentMethod(expenseDTO.getPaymentMethod());
        expense.setUser(user);
        expense.setCategory(category);

        Expense updatedExpense = expenseRepo.save(expense);

        ExpenseDTO response = new ExpenseDTO();
        response.setExpenseId(updatedExpense.getExpenseId());
        response.setTitle(updatedExpense.getTitle());
        response.setAmount(updatedExpense.getAmount());
        response.setExpenseDate(updatedExpense.getExpenseDate());
        response.setNote(updatedExpense.getNote());
        response.setPaymentMethod(updatedExpense.getPaymentMethod());
        response.setUserId(updatedExpense.getUser().getUserId());
        response.setCategoryId(updatedExpense.getCategory().getCategoryId());

        return response;
    }

    @Override
    public void deleteExpense(long expenseId) {
        expenseRepo.deleteById(expenseId);
    }
}