package com.example.springJPA.exception;

public class UserException extends IllegalArgumentException {

    public UserException(String msg) {
        super(msg);
    }
}
