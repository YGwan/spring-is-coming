package com.example.springJPA.dto;

public class UpdatePhoneNumberRequest {

    private Long id;
    private String name;
    private Integer age;
    private String phoneNumber;

    public UpdatePhoneNumberRequest() {
    }

    public UpdatePhoneNumberRequest(Long id, String name, Integer age, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public static UpdatePhoneNumberResponse of(UpdatePhoneNumberRequest request) {
        return new UpdatePhoneNumberResponse(request.getPhoneNumber(), request.getName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
