package com.chocolatestudios.ahorrapp.contexts.profiles.resources;

import java.time.LocalDateTime;

public class ProfileResource {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long userId;
    private LocalDateTime createdAt;


    public Long getId() {
        return id;
    }

    public ProfileResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ProfileResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfileResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public ProfileResource setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
