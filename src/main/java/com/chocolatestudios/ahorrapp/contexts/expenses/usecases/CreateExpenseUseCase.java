package com.chocolatestudios.ahorrapp.contexts.expenses.usecases;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts.expenses.repositories.ExpenseRepository;
import com.chocolatestudios.ahorrapp.contexts.expenses.resources.ExpenseResource;
import com.chocolatestudios.ahorrapp.contexts.expenses.resources.SaveExpenseResource;

@Service
public class CreateExpenseUseCase {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ExpenseRepository expenseRepository;

    public ExpenseResource createExpense(SaveExpenseResource saveExpenseResource) {
        return null;
    }
}
