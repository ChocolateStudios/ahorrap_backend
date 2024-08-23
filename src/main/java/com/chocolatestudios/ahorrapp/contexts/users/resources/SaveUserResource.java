package com.chocolatestudios.ahorrapp.contexts.users.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SaveUserResource {
    @NotBlank
    @NotNull
    @Size(max = 70)
    private String email;

    @NotBlank
    @NotNull
    @Size(max = 70)
    private String password;

    public String getEmail() {
        return email;
    }

    public SaveUserResource setEmail(String email) {
        this.email = email;
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
