package com.chocolatestudios.ahorrapp.contexts.expenses.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolatestudios.ahorrapp.contexts.expenses.resources.SaveExpenseResource;
import com.chocolatestudios.ahorrapp.contexts.expenses.resources.SynchronizedExpensesResource;
import com.chocolatestudios.ahorrapp.contexts.expenses.usecases.SynchronizeExpensesUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Expenses")
@RestController
@RequestMapping("/api/v1/expenses/sync")
@CrossOrigin
public class SynchronizeExpensesController {
    @Autowired
    private SynchronizeExpensesUseCase synchronizeExpensesUseCase;
    
    @Operation(summary = "Synchronize expenses", description = "Synchronize expenses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expenses synchronized", content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public SynchronizedExpensesResource synchronizeExpenses(@Valid @RequestBody List<SaveExpenseResource> saveExpenseResource) {
        var expenseResources = synchronizeExpensesUseCase.synchronizeExpenses(saveExpenseResource);
        return expenseResources;
    }
}
