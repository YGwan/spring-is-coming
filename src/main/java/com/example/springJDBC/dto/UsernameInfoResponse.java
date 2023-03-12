package com.example.springJDBC.dto;

import com.example.springJDBC.entity.User;

/*
    스프링은 빈(bean)으로 관리할때 디폴트는 첫대문자를 소문자로 바꿔서 저장한다.
    -> 클래스 명이랑 필드 명이랑 같으면 안좋다.
 */

public class UsernameInfoResponse {

    private Long id;
    private String username;
    private Integer age;
    private String phoneNumber;

    public UsernameInfoResponse() {
    }

    public UsernameInfoResponse(Long id, String username, Integer age, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.phoneNumber = "***-****-****";
    }

    public UsernameInfoResponse(User user) {
        this(user.getId(), user.getName(), user.getAge(), user.getPhoneNumber());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
