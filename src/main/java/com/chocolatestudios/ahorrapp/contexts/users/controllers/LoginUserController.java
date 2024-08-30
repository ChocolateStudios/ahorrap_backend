package com.chocolatestudios.ahorrapp.contexts.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolatestudios.ahorrapp.contexts.users.resources.AuthenticatedUserResource;
import com.chocolatestudios.ahorrapp.contexts.users.resources.SaveUserResource;
import com.chocolatestudios.ahorrapp.contexts.users.usecases.LoginUserUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;   

@Tag(name = "Users", description = "Authentication")
@RestController
@RequestMapping("/api/v1/users/login")
@CrossOrigin
public class LoginUserController {
    @Autowired
    private LoginUserUseCase loginUserUseCase;

    @Operation(summary = "Login User", description = "Login a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged", content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public AuthenticatedUserResource loginUser(@Valid @RequestBody SaveUserResource saveUserResource) {
        var userResource = loginUserUseCase.loginUser(saveUserResource);
        return userResource;
    }
}
