package com.personalfinance.expense_tracker.service;

import java.util.List;

import com.personalfinance.expense_tracker.dto.ExpenseDTO;

public interface ExpenseService {

    ExpenseDTO addExpense(ExpenseDTO expenseDTO);

    List<ExpenseDTO> getAllExpenses();

    ExpenseDTO getExpenseById(long expenseId);

    ExpenseDTO updateExpense(ExpenseDTO expenseDTO);

    void deleteExpense(long expenseId);
}