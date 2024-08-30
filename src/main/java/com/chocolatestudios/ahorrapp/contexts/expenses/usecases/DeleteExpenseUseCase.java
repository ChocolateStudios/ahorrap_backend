package com.chocolatestudios.ahorrapp.contexts.expenses.usecases;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts._shared.usecases.AuthenticatedUserUseCase;
import com.chocolatestudios.ahorrapp.contexts.expenses.models.Expense;
import com.chocolatestudios.ahorrapp.contexts.expenses.repositories.ExpenseRepository;
import com.chocolatestudios.ahorrapp.contexts.expenses.resources.ExpenseResource;
import com.chocolatestudios.ahorrapp.contexts.profiles.models.Profile;

@Service
public class DeleteExpenseUseCase extends AuthenticatedUserUseCase {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ExpenseRepository expenseRepository;

    public ExpenseResource deleteExpense(Long expenseId) {
        Profile authenticatedProfile = GetAuthenticatedProfileOrThrowException();

        Expense existingExpense = expenseRepository.findByProfileIdAndId(authenticatedProfile.getId(), expenseId);

        try {
            expenseRepository.delete(existingExpense);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the expense: " + e.getMessage());
        }

        return mapper.map(existingExpense, ExpenseResource.class);
    }
}
