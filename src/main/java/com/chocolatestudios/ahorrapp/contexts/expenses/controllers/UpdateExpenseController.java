package com.chocolatestudios.ahorrapp.contexts.expenses.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolatestudios.ahorrapp.contexts.expenses.resources.ExpenseResource;
import com.chocolatestudios.ahorrapp.contexts.expenses.resources.SaveExpenseResource;
import com.chocolatestudios.ahorrapp.contexts.expenses.usecases.UpdateExpenseUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Expenses")
@RestController
@RequestMapping("/api/v1/expenses/{expenseId}")
@CrossOrigin
public class UpdateExpenseController {
    @Autowired
    private UpdateExpenseUseCase updateExpenseUseCase;
    
    @Operation(summary = "Update expense", description = "Update an expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Expense updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping
    public ExpenseResource updateExpense(@PathVariable Long expenseId, @Valid @RequestBody SaveExpenseResource saveExpenseResource) {
        var expenseResource = updateExpenseUseCase.updateExpense(expenseId, saveExpenseResource);
        return expenseResource;
    }
}
