package com.example.springMVC.exception;

public class UserInfoException extends IllegalArgumentException {

    public UserInfoException(String msg) {
        super(msg);
    }
}
