package com.example.springJDBC.entity;

public class UserException extends IllegalArgumentException {

    public UserException(String msg) {
        super(msg);
    }
}
