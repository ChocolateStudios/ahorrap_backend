package com.chocolatestudios.ahorrapp.contexts.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolatestudios.ahorrapp.contexts.users.resources.SaveUserResource;
import com.chocolatestudios.ahorrapp.contexts.users.resources.UserResource;
import com.chocolatestudios.ahorrapp.contexts.users.usecases.UpdateUserUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;

@Tag(name = "Users", description = "Authentication")
@RestController
@RequestMapping("/api/v1/users/update")
@CrossOrigin
public class UpdateUserController {
    @Autowired
    private UpdateUserUseCase updateUserUseCase;
    
    
    @Operation(summary = "Update User", description = "Update a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping
    public UserResource updateUser(@RequestBody SaveUserResource saveUserResource) {
        var userResource = updateUserUseCase.updateUser(saveUserResource);
        return userResource;
    }
}