package com.chocolatestudios.ahorrapp.contexts.expenses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolatestudios.ahorrapp.contexts.expenses.resources.ExpenseResource;
import com.chocolatestudios.ahorrapp.contexts.expenses.usecases.DeleteExpenseUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Expenses")
@RestController
@RequestMapping("/api/v1/expenses")
@CrossOrigin
public class DeleteExpenseController {
    @Autowired
    private DeleteExpenseUseCase deleteExpenseUseCase;
    
    @Operation(summary = "Delete expense", description = "Delete an expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expense deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping
    public ExpenseResource createExpense() {
        var expenseResource = deleteExpenseUseCase.deleteExpense();
        return expenseResource;
    }
}
