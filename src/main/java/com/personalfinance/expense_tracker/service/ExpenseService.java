package com.personalfinance.expense_tracker.service;

import java.time.LocalDate;
import java.util.List;

import com.personalfinance.expense_tracker.dto.CategoryExpenseDTO;
import com.personalfinance.expense_tracker.dto.ExpenseDTO;
import com.personalfinance.expense_tracker.dto.MonthlyExpenseDTO;
import com.personalfinance.expense_tracker.dto.TotalExpenseDTO;

public interface ExpenseService {

    ExpenseDTO addExpense(ExpenseDTO expenseDTO);

    List<ExpenseDTO> getAllExpenses();

    ExpenseDTO getExpenseById(long expenseId);

    ExpenseDTO updateExpense(ExpenseDTO expenseDTO);

    void deleteExpense(long expenseId);
    List<ExpenseDTO> getExpensesByUser(long userId);

    List<ExpenseDTO> getExpensesByCategory(long categoryId);

    List<ExpenseDTO> getExpensesByDate(LocalDate startDate,
                                       LocalDate endDate);

    List<ExpenseDTO> getExpensesByPaymentMethod(String paymentMethod);
    
    TotalExpenseDTO getTotalExpense();
    
    List<CategoryExpenseDTO> getCategoryExpenseSummary();
    
    List<MonthlyExpenseDTO> getMonthlyExpenseSummary();
    
    TotalExpenseDTO getTodayExpense();
    
    TotalExpenseDTO getThisMonthExpense();
    
    List<ExpenseDTO> getRecentExpenses();
    
    long getExpenseCount();
}