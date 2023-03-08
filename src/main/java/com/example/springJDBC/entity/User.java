package com.example.springJDBC.entity;

public class User {

   private Long id;
   private String name;
   private Integer age;
   private String phoneNumber;
   // TODO : 공지) 사용자 정보로 휴대폰 번호가 추가되었습니다.

    public User() {
    }

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
