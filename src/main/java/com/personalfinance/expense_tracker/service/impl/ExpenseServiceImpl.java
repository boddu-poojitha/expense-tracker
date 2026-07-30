package com.personalfinance.expense_tracker.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.personalfinance.expense_tracker.entity.Expense;
import com.personalfinance.expense_tracker.repository.ExpenseRepository;
import com.personalfinance.expense_tracker.service.ExpenseService;


@Service
public class ExpenseServiceImpl implements ExpenseService{


	private final ExpenseRepository expenseRepo;
	public ExpenseServiceImpl(ExpenseRepository expenseRepo){
		this.expenseRepo=expenseRepo;
	}
	@Override
	public Expense addExpense(Expense expense) {
		return expenseRepo.save(expense);
	}
	@Override
	public List<Expense> getAllExpenses() {
		return expenseRepo.findAll();
	}
	@Override
	public Expense getExpenseById(long expenseId) {
		 return expenseRepo.findById(expenseId).orElse(null);	
	}
	@Override
	public Expense updateExpense(Expense expense) {
		 return expenseRepo.save(expense);
	}
	@Override
	public void deleteExpense(long expenseId) {
		expenseRepo.deleteById(expenseId);
	}

	
	
}
