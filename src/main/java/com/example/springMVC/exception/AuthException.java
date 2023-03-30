package com.example.springMVC.exception;

public class AuthException extends IllegalArgumentException {

    public AuthException(String msg) {
        super(msg);
    }
}
