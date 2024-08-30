package com.chocolatestudios.ahorrapp.contexts.expenses.resources;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class SaveExpenseResource {
    
    @NotBlank(message = "Description cannot be blank")
    @Size(max = 100, message = "Description must be at most 100 characters")
    @Column
    private String description;

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    @Column
    private BigDecimal amount;

    @NotNull(message = "Date cannot be null")
    @PastOrPresent(message = "Date must be in the past or present")
    @Column
    private LocalDate date;

    public String getDescription() {
        return description;
    }

    public SaveExpenseResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public SaveExpenseResource setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public SaveExpenseResource setDate(LocalDate date) {
        this.date = date;
        return this;
    }
}
