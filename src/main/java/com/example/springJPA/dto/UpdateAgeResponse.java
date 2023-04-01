package com.example.springJPA.dto;

public class UpdateAgeResponse {

    private Long id;
    private Integer age;

    public UpdateAgeResponse() {
    }

    public UpdateAgeResponse(Long id, Integer age) {
        this.id = id;
        this.age = age;
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
