package com.chocolatestudios.ahorrapp.contexts.profiles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolatestudios.ahorrapp.contexts.profiles.resources.ProfileResource;
import com.chocolatestudios.ahorrapp.contexts.profiles.resources.SaveProfileResource;
import com.chocolatestudios.ahorrapp.contexts.profiles.usecases.UpdateProfileUseCase;

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
public class UpdateProfileController {
    @Autowired
    private UpdateProfileUseCase updateProfileUseCase;
    
    
    @Operation(summary = "Update Profile", description = "Update current profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping
    public ProfileResource updateProfile(@Valid @RequestBody SaveProfileResource saveProfileResource) {
        var profileResource = updateProfileUseCase.updateProfile(saveProfileResource);
        return profileResource;
    }
}
