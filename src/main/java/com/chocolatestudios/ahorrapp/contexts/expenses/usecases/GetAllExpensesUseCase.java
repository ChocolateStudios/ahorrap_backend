package com.chocolatestudios.ahorrapp.contexts.expenses.usecases;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts._shared.usecases.AuthenticatedUserUseCase;
import com.chocolatestudios.ahorrapp.contexts.expenses.models.Expense;
import com.chocolatestudios.ahorrapp.contexts.expenses.repositories.ExpenseRepository;
import com.chocolatestudios.ahorrapp.contexts.expenses.resources.ExpenseResource;
import com.chocolatestudios.ahorrapp.contexts.profiles.models.Profile;

@Service
public class GetAllExpensesUseCase extends AuthenticatedUserUseCase {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ExpenseRepository expenseRepository;

    public List<ExpenseResource> getAllExpenses() {
        Profile authenticatedProfile = GetAuthenticatedProfileOrThrowException();
        
        List<Expense> expenses = expenseRepository.findAllByProfileId(authenticatedProfile.getId());

        return expenses.stream().map(expense -> mapper.map(expense, ExpenseResource.class)).toList();
    }
}
