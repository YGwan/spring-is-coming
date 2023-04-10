package com.example.springJPA.dto;

public class ReleaseRoomRequest {

    private String username;

    public ReleaseRoomRequest(String username) {
        this.username = username;
    }

    public ReleaseRoomRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
