package com.personalfinance.expense_tracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personalfinance.expense_tracker.entity.Expense;
import com.personalfinance.expense_tracker.service.ExpenseService;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
	
	private ExpenseService expenseSer;
	public ExpenseController(ExpenseService expenseSer) {
		this.expenseSer=expenseSer;
	}

	
	 @PostMapping
	    public Expense addExpense(@RequestBody Expense expense) {
	        return expenseSer.addExpense(expense);
	    }

	    @GetMapping
	    public List<Expense> getAllExpenses() {
	        return expenseSer.getAllExpenses();
	    }

	    @GetMapping("/{id}")
	    public Expense getExpenseById(@PathVariable long id) {
	        return expenseSer.getExpenseById(id);
	    }

	    @PutMapping
	    public Expense updateExpense(@RequestBody Expense expense) {
	        return expenseSer.updateExpense(expense);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteExpense(@PathVariable long id) {
	        expenseSer.deleteExpense(id);
	    }
}
