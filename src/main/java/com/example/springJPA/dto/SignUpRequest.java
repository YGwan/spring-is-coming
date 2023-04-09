package com.example.springJPA.dto;

import com.example.springJPA.entity.Sex;

import javax.validation.constraints.Email;

public class SignUpRequest {

    private String username;
    private String password;
    private String rePassword;
    private Sex sex;
    private Integer age;
    @Email
    private String email;
    private String name;
    private String phoneNumber;

    public SignUpRequest() {
    }

    public SignUpRequest(String username, String password, String rePassword, Sex sex,
                         Integer age, String email, String name, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.rePassword = rePassword;
        this.sex = sex;
        this.age = age;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
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
