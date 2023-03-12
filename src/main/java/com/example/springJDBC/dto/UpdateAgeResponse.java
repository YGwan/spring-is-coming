package com.example.springJDBC.dto;

import com.example.springJDBC.entity.User;

public class UpdateAgeResponse {

    private Long id;
    private String name;
    private Integer age;
    private String phoneNumber;

    public UpdateAgeResponse() {
    }

    public UpdateAgeResponse(Long id, String userName, Integer age, String phoneNumber) {
        this.id = id;
        this.name = userName;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public UpdateAgeResponse(User user) {
        this(user.getId(), user.getName(), user.getAge(), user.getPhoneNumber());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
