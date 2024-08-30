package com.chocolatestudios.ahorrapp.contexts.expenses.usecases;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts._shared.exceptions.ConflictException;
import com.chocolatestudios.ahorrapp.contexts._shared.usecases.AuthenticatedUserUseCase;
import com.chocolatestudios.ahorrapp.contexts.expenses.models.Expense;
import com.chocolatestudios.ahorrapp.contexts.expenses.repositories.ExpenseRepository;
import com.chocolatestudios.ahorrapp.contexts.expenses.resources.ExpenseResource;
import com.chocolatestudios.ahorrapp.contexts.expenses.resources.SaveExpenseResource;
import com.chocolatestudios.ahorrapp.contexts.profiles.models.Profile;

@Service
public class CreateExpenseUseCase extends AuthenticatedUserUseCase {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ExpenseRepository expenseRepository;

    public ExpenseResource createExpense(SaveExpenseResource saveExpenseResource) {
        Profile authenticatedProfile = GetAuthenticatedProfileOrThrowException();

        if (expenseRepository.existsByProfileIdAndAmountAndDateTime(authenticatedProfile.getId(),
                saveExpenseResource.getAmount(), saveExpenseResource.getDateTime())) {
            throw new ConflictException("Expense already exists with the same amount and date for authenticated profile");
        }

        Expense expense = mapper.map(saveExpenseResource, Expense.class);
        expense.setProfileId(authenticatedProfile.getId());

        try {
            expenseRepository.save(expense);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while creating the profile: " + e.getMessage());
        }

        return mapper.map(expense, ExpenseResource.class);
    }
}
