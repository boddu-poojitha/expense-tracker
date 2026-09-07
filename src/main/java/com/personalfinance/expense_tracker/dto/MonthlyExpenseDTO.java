package com.personalfinance.expense_tracker.dto;

public class MonthlyExpenseDTO {

    private String month;
    private Double totalExpense;

    public MonthlyExpenseDTO() {
    }

    public MonthlyExpenseDTO(Object month, Double totalExpense) {
        this.month = month != null ? month.toString() : null;
        this.totalExpense = totalExpense;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }
}