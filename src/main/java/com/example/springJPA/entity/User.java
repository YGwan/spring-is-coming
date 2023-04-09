package com.example.springJPA.entity;

import com.example.springJPA.exception.UserException;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private Integer age;
    @Email
    private String email;
    private String name;
    @Column(name = "PHONENUMBER")
    private String phoneNumber;

    public User() {
    }

    public User(String username, String password, Sex sex, Integer age, String email, String name, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public User(Long id, String username, String password, Sex sex, Integer age, String email, String name, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
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

    public void setAge(Integer age) {
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
