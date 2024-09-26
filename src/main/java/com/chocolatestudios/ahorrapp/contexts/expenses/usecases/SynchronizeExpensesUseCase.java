package com.chocolatestudios.ahorrapp.contexts.expenses.usecases;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolatestudios.ahorrapp.contexts._shared.exceptions.ConflictException;
import com.chocolatestudios.ahorrapp.contexts._shared.usecases.AuthenticatedUserUseCase;
import com.chocolatestudios.ahorrapp.contexts.expenses.models.Expense;
import com.chocolatestudios.ahorrapp.contexts.expenses.repositories.ExpenseRepository;
import com.chocolatestudios.ahorrapp.contexts.expenses.resources.ExpenseResource;
import com.chocolatestudios.ahorrapp.contexts.expenses.resources.SaveExpenseResource;
import com.chocolatestudios.ahorrapp.contexts.expenses.resources.SynchronizedExpensesResource;
import com.chocolatestudios.ahorrapp.contexts.profiles.models.Profile;

@Service
public class SynchronizeExpensesUseCase extends AuthenticatedUserUseCase {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ExpenseRepository expenseRepository;

    public SynchronizedExpensesResource synchronizeExpenses(List<SaveExpenseResource> saveExpenseResources) {
        System.out.println("ENTRANDO A SYNCHRO USECASE");

        Profile authenticatedProfile = GetAuthenticatedProfileOrThrowException();

        System.out.println("AUTHENTICATED PROFILE OBTENIDO");

        List<ExpenseResource> synchronizedExpenses = new ArrayList<>();
        List<String> errors = new ArrayList<>();

        System.out.println("INICIANDO EL AGREGADO DE EXPENSES");

        for (SaveExpenseResource saveExpenseResource : saveExpenseResources) {
            System.out.println("INICIANDO CON EXPENSE X");

            if (expenseRepository.existsByProfileIdAndAmountAndDateTime(authenticatedProfile.getId(),
                    saveExpenseResource.getAmount(), saveExpenseResource.getDateTime())) {
                var error = new ConflictException("Expense already exists with the same amount and date for authenticated profile");
                errors.add(error.getMessage());
                continue;
            }
            System.out.println("VAMOS BIEN");

            Expense expense = mapper.map(saveExpenseResource, Expense.class);
            expense.setProfileId(authenticatedProfile.getId());

            try {
                expenseRepository.save(expense);
                synchronizedExpenses.add(mapper.map(expense, ExpenseResource.class));
                System.out.println("SIGUIENTE!!");
            } catch (Exception e) {
                var error = new RuntimeException("An error occurred while creating the profile: " + e.getMessage());
                errors.add(error.getMessage());
                continue;
            }
        }

        return new SynchronizedExpensesResource().setExpenses(synchronizedExpenses).setErrors(errors);
    }
}