package com.example.springMVC.dto;

import com.example.springMVC.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponse {

    private Long id;
    private String name;
    private int age;
    private String phoneNumber;

    public UserResponse() {
    }

    public UserResponse(Long id, String name, int age, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public static UserResponse of(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getAge(), user.getPhoneNumber());
    }

    public static List<UserResponse> listOf(List<User> users) {
        return users.stream()
                .map(UserResponse::of)
                .collect(Collectors.toList());
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

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
