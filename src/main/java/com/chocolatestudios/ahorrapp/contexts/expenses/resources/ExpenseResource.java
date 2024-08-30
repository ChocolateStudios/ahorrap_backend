package com.chocolatestudios.ahorrapp.contexts.expenses.resources;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExpenseResource {
    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDateTime date;
    private Long profileId;

    public Long getId() {
        return id;
    }
    public ExpenseResource setId(Long id) {
        this.id = id;
        return this;
    }
    public String getDescription() {
        return description;
    }
    public ExpenseResource setDescription(String description) {
        this.description = description;
        return this;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public ExpenseResource setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public ExpenseResource setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }
    public Long getProfileId() {
        return profileId;
    }
    public ExpenseResource setProfileId(Long profileId) {
        this.profileId = profileId;
        return this;
    }
}
