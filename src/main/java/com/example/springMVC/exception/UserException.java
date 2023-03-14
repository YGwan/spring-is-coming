package com.example.springMVC.exception;

public class UserException extends IllegalArgumentException {

    public UserException(String msg) {
        super(msg);
    }
}
