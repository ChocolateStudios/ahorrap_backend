package com.chocolatestudios.ahorrapp.contexts.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolatestudios.ahorrapp.contexts.users.resources.UserResource;
import com.chocolatestudios.ahorrapp.contexts.users.usecases.GetUserUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;

@Tag(name = "Users", description = "Authentication")
@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class GetUserController {
    @Autowired
    private GetUserUseCase getUserUseCase;

    @Operation(summary = "Get User", description = "Get a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged", content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public UserResource getUser() {
        var userResource = getUserUseCase.getUser();
        return userResource;
    }
}