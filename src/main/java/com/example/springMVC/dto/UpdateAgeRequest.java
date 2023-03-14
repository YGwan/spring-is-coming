package com.example.springhello.dto;

public class UpdateAgeRequest {

    private Long id;
    private int age;

    public UpdateAgeRequest() {
    }

    public UpdateAgeRequest(Long id, int age) {
        this.id = id;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
