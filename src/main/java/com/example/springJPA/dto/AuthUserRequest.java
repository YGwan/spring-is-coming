package com.example.springJPA.dto;

import javax.validation.constraints.NotNull;

public class AuthUserRequest {

    @NotNull
    private String token;

    public AuthUserRequest() {
    }

    public AuthUserRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
