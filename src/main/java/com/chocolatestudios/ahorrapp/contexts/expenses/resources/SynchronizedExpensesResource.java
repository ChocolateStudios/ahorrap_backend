package com.chocolatestudios.ahorrapp.contexts.expenses.resources;

import java.util.List;

public class SynchronizedExpensesResource {
    private List<ExpenseResource> expenses;
    private List<String> errors;

    public List<ExpenseResource> getExpenses() {
        return expenses;
    }

    public SynchronizedExpensesResource setExpenses(List<ExpenseResource> expenses) {
        this.expenses = expenses;
        return this;
    }

    public List<String> getErrors() {
        return errors;
    }

    public SynchronizedExpensesResource setErrors(List<String> conflicts) {
        this.errors = conflicts;
        return this;
    }
}
