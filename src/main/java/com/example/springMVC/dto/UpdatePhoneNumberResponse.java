package com.example.springMVC.dto;

public class UpdatePhoneNumberResponse {

    private String phoneNumber;
    private String name;

    public UpdatePhoneNumberResponse() {
    }

    public UpdatePhoneNumberResponse(String phoneNumber, String name) {
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
