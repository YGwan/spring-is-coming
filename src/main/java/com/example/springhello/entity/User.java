package com.example.springhello.entity;

import com.example.springhello.exception.UserException;

public class User {

    private Long id;
    private String name;
    private Integer age;
    private String phoneNumber;

    public User() {
    }

    public User(Long id, String name, Integer age, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public void validateEqualByNameAndAge(User realUser) {
        if(!(realUser.getName().equals(this.name)) || !(realUser.getAge().equals(this.age))) {
            throw new UserException("정보 불일치");
        }
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
