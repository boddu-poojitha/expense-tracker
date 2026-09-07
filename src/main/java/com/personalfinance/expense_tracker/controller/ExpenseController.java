package com.personalfinance.expense_tracker.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.personalfinance.expense_tracker.dto.CategoryExpenseDTO;
import com.personalfinance.expense_tracker.dto.ExpenseDTO;
import com.personalfinance.expense_tracker.dto.MonthlyExpenseDTO;
import com.personalfinance.expense_tracker.service.ExpenseService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.personalfinance.expense_tracker.dto.TotalExpenseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/expenses")
@SecurityRequirement(name = "bearerAuth")
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
    
    @GetMapping("/user/{userId}")
    public List<ExpenseDTO> getExpensesByUser(@PathVariable long userId) {

        return expenseService.getExpensesByUser(userId);
    }

    @GetMapping("/category/{categoryId}")
    public List<ExpenseDTO> getExpensesByCategory(@PathVariable long categoryId) {

        return expenseService.getExpensesByCategory(categoryId);
    }

    @GetMapping("/date")
    public List<ExpenseDTO> getExpensesByDate(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        return expenseService.getExpensesByDate(startDate, endDate);
    }

    @GetMapping("/payment/{paymentMethod}")
    public List<ExpenseDTO> getExpensesByPaymentMethod(
            @PathVariable String paymentMethod) {

        return expenseService.getExpensesByPaymentMethod(paymentMethod);
    }
    
    @GetMapping("/dashboard/total-expense")
    public TotalExpenseDTO getTotalExpense() {

        return expenseService.getTotalExpense();
    }
    
    @GetMapping("/dashboard/category-summary")
    public List<CategoryExpenseDTO> getCategoryExpenseSummary() {

        return expenseService.getCategoryExpenseSummary();
    }
    
    @GetMapping("/dashboard/monthly-summary")
    public List<MonthlyExpenseDTO> getMonthlyExpenseSummary() {

        return expenseService.getMonthlyExpenseSummary();
    }
    
    @GetMapping("/dashboard/today")
    public TotalExpenseDTO getTodayExpense() {

        return expenseService.getTodayExpense();
    }
    
    @GetMapping("/dashboard/this-month")
    public TotalExpenseDTO getThisMonthExpense() {

        return expenseService.getThisMonthExpense();
    }
    
    @GetMapping("/dashboard/recent")
    public List<ExpenseDTO> getRecentExpenses() {
        return expenseService.getRecentExpenses();
    }
    
    @GetMapping("/dashboard/expense-count")
    public long getExpenseCount() {
        return expenseService.getExpenseCount();
    }
}