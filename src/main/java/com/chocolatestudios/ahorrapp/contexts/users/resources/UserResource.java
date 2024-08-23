package com.chocolatestudios.ahorrapp.contexts.users.resources;

public class UserResource {
    private Long id;
    private String email;

    public Long getId() {
        return id;
    }
    public UserResource setId(Long id) {
        this.id = id;
        return this;
    }
    public String getEmail() {
        return email;
    }
    public UserResource setEmail(String email) {
        this.email = email;
        return this;
    }
}
