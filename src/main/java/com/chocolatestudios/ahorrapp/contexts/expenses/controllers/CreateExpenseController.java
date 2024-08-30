package com.chocolatestudios.ahorrapp.contexts.expenses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolatestudios.ahorrapp.contexts.expenses.resources.ExpenseResource;
import com.chocolatestudios.ahorrapp.contexts.expenses.resources.SaveExpenseResource;
import com.chocolatestudios.ahorrapp.contexts.expenses.usecases.CreateExpenseUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Expenses")
@RestController
@RequestMapping("/api/v1/expenses")
@CrossOrigin
public class CreateExpenseController {
    @Autowired
    private CreateExpenseUseCase createExpenseUseCase;
    
    @Operation(summary = "Create expense", description = "Create an expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expense created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ExpenseResource createExpense(@Valid @RequestBody SaveExpenseResource saveExpenseResource) {
        var expenseResource = createExpenseUseCase.createExpense(saveExpenseResource);
        return expenseResource;
    }
}
