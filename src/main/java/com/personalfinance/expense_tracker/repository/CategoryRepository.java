package com.personalfinance.expense_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personalfinance.expense_tracker.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
