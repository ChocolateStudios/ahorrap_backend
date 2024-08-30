package com.chocolatestudios.ahorrapp.contexts.admins.controllers.usecases.profiles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolatestudios.ahorrapp.contexts.admins.usecases.profiles.GetAllProfilesUseCase;
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
public class GetAllProfilesController {
    @Autowired
    private GetAllProfilesUseCase getAllProfilesUseCase;

    @Operation(summary = "Get All Profiles", description = "Get all profiles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = { @Content(mediaType = "application/json") }),
    })
    @GetMapping
    public List<UserResource> getAllProfiles() {
        var userResources = getAllProfilesUseCase.getAllProfiles();
        return userResources;
    }
}
