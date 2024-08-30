package com.chocolatestudios.ahorrapp.contexts.profiles.resources;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SaveProfileResource {

    @NotBlank(message = "First name cannot be blank")
    @Size(max = 100, message = "First name cannot exceed 100 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only letters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 100, message = "Last name cannot exceed 100 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only letters")
    private String lastName;

    @Email(message = "Email must be a valid format")
    @NotBlank(message = "Email cannot be blank")
    @Size(max = 70, message = "Email cannot exceed 70 characters")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public SaveProfileResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SaveProfileResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SaveProfileResource setEmail(String email) {
        this.email = email;
        return this;
    }
}
