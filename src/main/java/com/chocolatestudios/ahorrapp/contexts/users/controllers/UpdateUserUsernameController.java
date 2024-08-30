package com.chocolatestudios.ahorrapp.contexts.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolatestudios.ahorrapp.contexts.users.resources.AuthenticatedUserResource;
import com.chocolatestudios.ahorrapp.contexts.users.resources.SaveUserResource;
import com.chocolatestudios.ahorrapp.contexts.users.usecases.UpdateUserUsernameUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;

@Tag(name = "Users", description = "Authentication")
@RestController
@RequestMapping("/api/v1/users/update/username")
@CrossOrigin
public class UpdateUserUsernameController {
    @Autowired
    private UpdateUserUsernameUseCase updateUserUsernameUseCase;
    
    
    @Operation(summary = "Update Username", description = "Update authenticated user username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Username updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping
    public AuthenticatedUserResource updateUserUsername(@RequestBody SaveUserResource saveUserResource) {
        var userResource = updateUserUsernameUseCase.updateUserUsername(saveUserResource);
        return userResource;
    }
}