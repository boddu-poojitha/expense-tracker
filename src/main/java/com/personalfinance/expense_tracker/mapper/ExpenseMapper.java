package com.personalfinance.expense_tracker.mapper;

import com.personalfinance.expense_tracker.dto.ExpenseDTO;
import com.personalfinance.expense_tracker.entity.Category;
import com.personalfinance.expense_tracker.entity.Expense;
import com.personalfinance.expense_tracker.entity.User;

public class ExpenseMapper {

    public static ExpenseDTO toDTO(Expense expense) {

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

        if (expense.getUser() != null) {
            dto.setUserId(expense.getUser().getUserId());
        }

        if (expense.getCategory() != null) {
            dto.setCategoryId(expense.getCategory().getCategoryId());
        }

        return dto;
    }

    public static Expense toEntity(ExpenseDTO dto) {

        if (dto == null) {
            return null;
        }

        Expense expense = new Expense();

        expense.setExpenseId(dto.getExpenseId());
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setNote(dto.getNote());
        expense.setPaymentMethod(dto.getPaymentMethod());

        User user = new User();
        user.setUserId(dto.getUserId());
        expense.setUser(user);

        Category category = new Category();
        category.setCategoryId(dto.getCategoryId());
        expense.setCategory(category);

        return expense;
    }
}