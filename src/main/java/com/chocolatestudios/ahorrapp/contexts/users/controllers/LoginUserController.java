package com.chocolatestudios.ahorrapp.contexts.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolatestudios.ahorrapp.contexts.users.resources.SaveUserResource;
import com.chocolatestudios.ahorrapp.contexts.users.resources.UserResource;
import com.chocolatestudios.ahorrapp.contexts.users.usecases.LoginUserUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;    

@Tag(name = "Users", description = "Authentication")
@RestController
@RequestMapping("/api/v1/users/login")
@CrossOrigin
public class LoginUserController {
    @Autowired
    private LoginUserUseCase loginUserUseCase;

    @Operation(summary = "Login User", description = "Login a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged", content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public UserResource loginUser(@RequestBody SaveUserResource saveUserResource) {
        var userResource = loginUserUseCase.loginUser(saveUserResource);
        return userResource;
    }
}
