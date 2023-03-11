package com.example.springJDBC.dto;

import com.example.springJDBC.entity.User;

/*
    스프링은 빈(bean)으로 관리할때 디폴트는 첫대문자를 소문자로 바꿔서 저장한다.
    -> 클래스 명이랑 필드 명이랑 같으면 안좋다.
 */

public class UserNameInfoResponse {

    private Long id;
    private String userName;
    private Integer age;
    private String phoneNumber;

    public UserNameInfoResponse() {
    }

    public UserNameInfoResponse(Long id, String userName, Integer age, String phoneNumber) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public UserNameInfoResponse(User user) {
        this(user.getId(), user.getName(), user.getAge(), null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
