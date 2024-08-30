package com.chocolatestudios.ahorrapp.contexts.expenses.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolatestudios.ahorrapp.contexts.expenses.resources.ExpenseResource;
import com.chocolatestudios.ahorrapp.contexts.expenses.usecases.GetAllExpensesUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Expenses")
@RestController
@RequestMapping("/api/v1/expenses")
@CrossOrigin
public class GetAllExpensesController {
    @Autowired
    private GetAllExpensesUseCase getAllExpensesUseCase;
    
    @Operation(summary = "Create expense", description = "Create an expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expense created", content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public List<ExpenseResource> getAllExpenses() {
        var expenseResources = getAllExpensesUseCase.getAllExpenses();
        return expenseResources;
    }
}
