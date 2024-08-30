package com.chocolatestudios.ahorrapp.contexts.expenses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chocolatestudios.ahorrapp.contexts.expenses.models.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
