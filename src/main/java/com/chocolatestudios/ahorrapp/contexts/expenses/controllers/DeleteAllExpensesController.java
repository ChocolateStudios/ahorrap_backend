package com.chocolatestudios.ahorrapp.contexts.expenses.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolatestudios.ahorrapp.contexts.expenses.resources.ExpenseResource;
import com.chocolatestudios.ahorrapp.contexts.expenses.usecases.DeleteAllExpensesUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Expensess")
@RestController
@RequestMapping("/api/v1/expenses")
@CrossOrigin
public class DeleteAllExpensesController {
    @Autowired
    private DeleteAllExpensesUseCase deleteAllExpensesUseCase;
    
    @Operation(summary = "DeleteAll expense", description = "DeleteAll an expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expenses deleteAlld", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping
    public List<ExpenseResource> deleteAllExpenses() {
        var expenseResources = deleteAllExpensesUseCase.deleteAllExpenses();
        return expenseResources;
    }
}
