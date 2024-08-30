package com.chocolatestudios.ahorrapp.contexts.expenses.repositories;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chocolatestudios.ahorrapp.contexts.expenses.models.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    boolean existsByProfileIdAndAmountAndDateTime(Long profileId, BigDecimal amount, LocalDateTime dateTime);
    Expense findByProfileIdAndId(Long profileId, Long expenseId);
    List<Expense> findAllByProfileId(Long profileId);
}
