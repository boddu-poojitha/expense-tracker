package com.personalfinance.expense_tracker.service;

import java.util.List;

import com.personalfinance.expense_tracker.entity.Expense;


public interface ExpenseService {

	 
	    Expense addExpense(Expense expense);

	    List<Expense> getAllExpenses();

	    Expense getExpenseById(long expenseId);

	    Expense updateExpense(Expense expense);

	    void deleteExpense(long expenseId);
}
