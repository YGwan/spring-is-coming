package com.example.springMVC.dto;

import com.example.springMVC.entity.User;

public class UpdateAgeResponse {

    private Long id;
    private Integer age;

    public UpdateAgeResponse() {
    }

    public UpdateAgeResponse(Long id, Integer age) {
        this.id = id;
        this.age = age;
    }

    public UpdateAgeResponse(User user) {
        this(user.getId(), user.getAge());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
