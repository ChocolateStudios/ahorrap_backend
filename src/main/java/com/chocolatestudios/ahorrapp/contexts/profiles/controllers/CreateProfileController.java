package com.chocolatestudios.ahorrapp.contexts.profiles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolatestudios.ahorrapp.contexts.profiles.resources.ProfileResource;
import com.chocolatestudios.ahorrapp.contexts.profiles.resources.SaveProfileResource;
import com.chocolatestudios.ahorrapp.contexts.profiles.usecases.CreateProfileUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Profiles")
@RestController
@RequestMapping("/api/v1/profiles")
@CrossOrigin
public class CreateProfileController {
    @Autowired
    private CreateProfileUseCase createProfileUseCase;
    
    @Operation(summary = "Create Profile", description = "Create a Profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ProfileResource createProfile(@Valid @RequestBody SaveProfileResource saveProfileResource) {
        var profileResource = createProfileUseCase.createProfile(saveProfileResource);
        return profileResource;
    }
}
