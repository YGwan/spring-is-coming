package com.example.springMVC.dto;

import com.example.springMVC.entity.Person;

import javax.validation.constraints.Email;

public class PersonResponse {

    Long id;
    String username;
    String password;
    int age;
    @Email
    String email;
    String name;
    String phoneNumber;

    public PersonResponse() {
    }

    public PersonResponse(Long id, String username, String password, int age, String email, String name, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public static PersonResponse of(Person person) {
        return new PersonResponse(
                person.getId(), person.getUsername(), person.getPassword(),
                person.getAge(), person.getEmail(), person.getName(), person.getPhoneNumber()
        );
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

