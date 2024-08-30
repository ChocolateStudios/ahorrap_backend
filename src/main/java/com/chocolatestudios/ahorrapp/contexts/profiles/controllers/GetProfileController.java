package com.chocolatestudios.ahorrapp.contexts.profiles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolatestudios.ahorrapp.contexts.profiles.resources.ProfileResource;
import com.chocolatestudios.ahorrapp.contexts.profiles.usecases.GetProfileUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Profiles")
@RestController
@RequestMapping("/api/v1/profiles")
@CrossOrigin
public class GetProfileController {
    @Autowired
    private GetProfileUseCase getProfileUseCase;
    
    
    @Operation(summary = "Get Profile", description = "Get current profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile obtained", content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public ProfileResource getProfile() {
        var profileResource = getProfileUseCase.getProfile();
        return profileResource;
    }
}
