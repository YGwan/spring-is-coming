package com.example.springMVC.dto;

public class AuthUserRequest {

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
