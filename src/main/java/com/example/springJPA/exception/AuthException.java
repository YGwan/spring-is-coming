package com.example.springJPA.exception;

public class AuthException extends IllegalArgumentException {

    public AuthException(String msg) {
        super(msg);
    }
}
