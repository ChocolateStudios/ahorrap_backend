package com.chocolatestudios.ahorrapp.contexts._shared.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Hello World")
@RestController
@RequestMapping("/api/v1/helloworld")
@CrossOrigin
public class HelloWorldController {
    @Operation(summary = "Hello World", description = "Endpoint to wake up the application")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hello World", content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public Map<String, String> helloWorld() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello World");
        return response;
    }
}
