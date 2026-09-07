package com.personalfinance.expense_tracker.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.personalfinance.expense_tracker.dto.CategoryExpenseDTO;
import com.personalfinance.expense_tracker.dto.MonthlyExpenseDTO;
import com.personalfinance.expense_tracker.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser_UserId(long userId);

    List<Expense> findByCategory_CategoryId(long categoryId);

    List<Expense> findByExpenseDateBetween(LocalDate startDate,
                                           LocalDate endDate);

    List<Expense> findByPaymentMethod(String paymentMethod);
    
    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e")
    double getTotalExpense();
    
    @Query("""
    		SELECT new com.personalfinance.expense_tracker.dto.CategoryExpenseDTO(
    		c.categoryName,
    		COALESCE(SUM(e.amount),0)
    		)
    		FROM Expense e
    		JOIN e.category c
    		GROUP BY c.categoryName
    		""")
    		List<CategoryExpenseDTO> getCategoryExpenseSummary();
    
    
    @Query("""
    	    SELECT new com.personalfinance.expense_tracker.dto.MonthlyExpenseDTO(
    	        FUNCTION('DATE_FORMAT', e.expenseDate, '%Y-%m'),
    	        COALESCE(SUM(e.amount), 0)
    	    )
    	    FROM Expense e
    	    GROUP BY FUNCTION('DATE_FORMAT', e.expenseDate, '%Y-%m')
    	    ORDER BY FUNCTION('DATE_FORMAT', e.expenseDate, '%Y-%m')
    	""")
    	List<MonthlyExpenseDTO> getMonthlyExpenseSummary();
    
    @Query("""
    	    SELECT COALESCE(SUM(e.amount), 0)
    	    FROM Expense e
    	    WHERE e.expenseDate = CURRENT_DATE
    	    """)
    	double getTodayExpense();

    
    @Query("""
    	    SELECT COALESCE(SUM(e.amount), 0)
    	    FROM Expense e
    	    WHERE YEAR(e.expenseDate) = YEAR(CURRENT_DATE)
    	      AND MONTH(e.expenseDate) = MONTH(CURRENT_DATE)
    	    """)
    	double getThisMonthExpense();
    
    List<Expense> findTop5ByOrderByExpenseDateDescExpenseIdDesc();
    
    @Query("SELECT COUNT(e) FROM Expense e")
    long getExpenseCount();
}