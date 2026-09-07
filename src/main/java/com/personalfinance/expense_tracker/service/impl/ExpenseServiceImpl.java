package com.personalfinance.expense_tracker.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.personalfinance.expense_tracker.dto.CategoryExpenseDTO;
import com.personalfinance.expense_tracker.dto.ExpenseDTO;
import com.personalfinance.expense_tracker.dto.MonthlyExpenseDTO;
import com.personalfinance.expense_tracker.entity.Category;
import com.personalfinance.expense_tracker.entity.Expense;
import com.personalfinance.expense_tracker.entity.User;
import com.personalfinance.expense_tracker.exception.ResourceNotFoundException;
import com.personalfinance.expense_tracker.mapper.ExpenseMapper;
import com.personalfinance.expense_tracker.repository.CategoryRepository;
import com.personalfinance.expense_tracker.repository.ExpenseRepository;
import com.personalfinance.expense_tracker.repository.UserRepository;
import com.personalfinance.expense_tracker.service.ExpenseService;
import com.personalfinance.expense_tracker.dto.TotalExpenseDTO;

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
    
    
    @Override
    public List<ExpenseDTO> getExpensesByUser(long userId) {

        return expenseRepo.findByUser_UserId(userId)
                .stream()
                .map(ExpenseMapper::toDTO)
                .toList();
    }

    @Override
    public List<ExpenseDTO> getExpensesByCategory(long categoryId) {

        return expenseRepo.findByCategory_CategoryId(categoryId)
                .stream()
                .map(ExpenseMapper::toDTO)
                .toList();
    }

    @Override
    public List<ExpenseDTO> getExpensesByDate(LocalDate startDate,
                                              LocalDate endDate) {

        return expenseRepo.findByExpenseDateBetween(startDate, endDate)
                .stream()
                .map(ExpenseMapper::toDTO)
                .toList();
    }

    @Override
    public List<ExpenseDTO> getExpensesByPaymentMethod(String paymentMethod) {

        return expenseRepo.findByPaymentMethod(paymentMethod)
                .stream()
                .map(ExpenseMapper::toDTO)
                .toList();
    }
    
    @Override
    public TotalExpenseDTO getTotalExpense() {

        double total = expenseRepo.getTotalExpense();

        return new TotalExpenseDTO(total);
    }
    
    @Override
    public List<CategoryExpenseDTO> getCategoryExpenseSummary() {

        return expenseRepo.getCategoryExpenseSummary();
    }
    
    
    @Override
    public List<MonthlyExpenseDTO> getMonthlyExpenseSummary() {

        return expenseRepo.getMonthlyExpenseSummary();
    }
    
    
    @Override
    public TotalExpenseDTO getTodayExpense() {

        double total = expenseRepo.getTodayExpense();

        return new TotalExpenseDTO(total);
    }
    
    @Override
    public TotalExpenseDTO getThisMonthExpense() {

        double total = expenseRepo.getThisMonthExpense();

        return new TotalExpenseDTO(total);
    }
    
    
    @Override
    public List<ExpenseDTO> getRecentExpenses() {

        List<Expense> expenses =
                expenseRepo.findTop5ByOrderByExpenseDateDescExpenseIdDesc();

        return expenses.stream()
                .map(ExpenseMapper::toDTO)
                .toList();
    }
    
    @Override
    public long getExpenseCount() {
        return expenseRepo.getExpenseCount();
    }
}