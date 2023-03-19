package com.example.springMVC.dto;

public class SignUpRequest {

    private Long id;
    private String username;
    private String password;
    private String rePassword;
    private int age;
    private String email;
    private String name;
    private String phoneNumber;

    public SignUpRequest() {
    }

    public SignUpRequest(Long id, String username, String password, String rePassword,
                         int age, String email, String name, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rePassword = rePassword;
        this.age = age;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
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

    public String getRePassword() {
        return rePassword.trim();
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public int getAge() {
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
