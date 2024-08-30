package com.chocolatestudios.ahorrapp.contexts.users.resources;

public class AuthenticatedUserResource {
    private Long id;
    private String username;
    private String token;

    public Long getId() {
        return id;
    }
    public AuthenticatedUserResource setId(Long id) {
        this.id = id;
        return this;
    }
    public String getUsername() {
        return username;
    }
    public AuthenticatedUserResource setUsername(String username) {
        this.username = username;
        return this;
    }
    public String getToken() {
        return token;
    }
    public AuthenticatedUserResource setToken(String token) {
        this.token = token;
        return this;
    }
}
