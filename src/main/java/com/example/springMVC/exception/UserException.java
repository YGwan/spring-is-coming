package com.example.springhello.exception;

public class UserException extends IllegalArgumentException {

    public UserException(String msg) {
        super(msg);
    }
}
