package com.example.springJPA.dto;

public class ReserveRoomRequest {

    private String username;

    public ReserveRoomRequest(String username) {
        this.username = username;
    }

    public ReserveRoomRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
