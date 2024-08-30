package com.chocolatestudios.ahorrapp.contexts.expenses.usecases;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts._shared.exceptions.ResourceNotFoundException;
import com.chocolatestudios.ahorrapp.contexts._shared.usecases.AuthenticatedUserUseCase;
import com.chocolatestudios.ahorrapp.contexts.expenses.models.Expense;
import com.chocolatestudios.ahorrapp.contexts.expenses.repositories.ExpenseRepository;
import com.chocolatestudios.ahorrapp.contexts.expenses.resources.ExpenseResource;
import com.chocolatestudios.ahorrapp.contexts.expenses.resources.SaveExpenseResource;
import com.chocolatestudios.ahorrapp.contexts.profiles.models.Profile;

@Service
public class UpdateExpenseUseCase extends AuthenticatedUserUseCase {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ExpenseRepository expenseRepository;

    public ExpenseResource updateExpense(Long expenseId, SaveExpenseResource saveExpenseResource) {
        Profile authenticatedProfile = GetAuthenticatedProfileOrThrowException();

        Expense existingExpense = expenseRepository.findByProfileIdAndId(authenticatedProfile.getId(), expenseId);

        if (existingExpense == null)
            throw new ResourceNotFoundException("Expense not found for authenticated profile with value expenseId: " + expenseId);
        
        existingExpense.setAmount(saveExpenseResource.getAmount());
        existingExpense.setDateTime(saveExpenseResource.getDateTime());
        existingExpense.setDescription(saveExpenseResource.getDescription());

        try {
            expenseRepository.save(existingExpense);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the expense: " + e.getMessage());
        }

        return mapper.map(existingExpense, ExpenseResource.class);
    }
}
