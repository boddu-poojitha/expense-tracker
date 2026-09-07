package com.personalfinance.expense_tracker.dto;

public class TotalExpenseDTO {

    private double totalExpense;

    public TotalExpenseDTO() {
    }

    public TotalExpenseDTO(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }
}