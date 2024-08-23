package com.chocolatestudios.ahorrapp.contexts.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolatestudios.ahorrapp.contexts.users.resources.SaveUserResource;
import com.chocolatestudios.ahorrapp.contexts.users.resources.UserResource;
import com.chocolatestudios.ahorrapp.contexts.users.usecases.RegisterUserUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;

@Tag(name = "Users", description = "Authentication")
@RestController
@RequestMapping("/api/v1/users/register")
@CrossOrigin
public class RegisterUserController {
    @Autowired
    private RegisterUserUseCase registerUserUseCase;
    
    
    @Operation(summary = "Register User", description = "Register a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered", content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public UserResource registerUser(@RequestBody SaveUserResource saveUserResource) {
        var userResource = registerUserUseCase.registerUser(saveUserResource);
        return userResource;
    }
}
