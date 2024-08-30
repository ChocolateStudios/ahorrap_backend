package com.chocolatestudios.ahorrapp.contexts.admins.controllers.usecases.profiles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolatestudios.ahorrapp.contexts.admins.usecases.profiles.CreateProfilesUseCase;
import com.chocolatestudios.ahorrapp.contexts.users.resources.SaveUserResource;
import com.chocolatestudios.ahorrapp.contexts.users.resources.UserResource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;

@Tag(name = "Admins")
@RestController
@RequestMapping("/api/v1/admins/profiles")
@CrossOrigin
public class CreateProfilesController {
    @Autowired
    private CreateProfilesUseCase createProfilesUseCase;

    @Operation(summary = "Create Profiles", description = "Create Profiles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = { @Content(mediaType = "application/json") }),
    })
    @PostMapping
    public List<UserResource> createProfiles(@RequestBody List<SaveUserResource> saveUserResources) {
        var userResources = createProfilesUseCase.createProfiles(saveUserResources);
        return userResources;
    }
}
