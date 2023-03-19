package com.example.springMVC.entity;

import com.example.springMVC.exception.UserException;

import javax.validation.constraints.Email;

public class User {

    private Long id;
    private String username;
    private String password;
    private Integer age;
    @Email(message = "이메일 fcuck")
    private String email;
    private String name;
    private String phoneNumber;

    public User() {
    }

    public User(Long id, String username, String password, Integer age, String email, String name, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void validateEqualByNameAndAge(String name, Integer age) throws UserException {
        if (!(name.equals(this.name)) || !(age.equals(this.age))) {
            throw new UserException("정보 불일치");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username.trim();
    }

    public void setUsername(String username) {
        this.username = username.trim();
    }

    public String getPassword() {
        return password.trim();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email.trim();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name.trim();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber.trim();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
