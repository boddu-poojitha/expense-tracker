package com.personalfinance.expense_tracker.dto;

public class CategoryExpenseDTO {

    private String category;
    private double totalAmount;

    public CategoryExpenseDTO() {
    }

    public CategoryExpenseDTO(String category, double totalAmount) {
        this.category = category;
        this.totalAmount = totalAmount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}