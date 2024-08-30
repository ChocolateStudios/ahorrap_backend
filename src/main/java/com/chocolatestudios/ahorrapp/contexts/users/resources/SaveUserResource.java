package com.chocolatestudios.ahorrapp.contexts.users.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SaveUserResource {

    @NotBlank(message = "Username cannot be blank")
    @Size(max = 70, message = "Username cannot exceed 70 characters")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(max = 70, message = "Password cannot exceed 255 characters")
    private String password;

    public String getUsername() {
        return username;
    }

    public SaveUserResource setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SaveUserResource setPassword(String password) {
        this.password = password;
        return this;
    }
}
