package com.personalfinance.expense_tracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.personalfinance.expense_tracker.dto.ExpenseDTO;
import com.personalfinance.expense_tracker.service.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ExpenseDTO addExpense(@Valid @RequestBody ExpenseDTO expenseDTO) {
        return expenseService.addExpense(expenseDTO);
    }

    @GetMapping
    public List<ExpenseDTO> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public ExpenseDTO getExpenseById(@PathVariable long id) {
        return expenseService.getExpenseById(id);
    }

    @PutMapping
    public ExpenseDTO updateExpense(@Valid @RequestBody ExpenseDTO expenseDTO) {
        return expenseService.updateExpense(expenseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable long id) {
        expenseService.deleteExpense(id);
    }
}