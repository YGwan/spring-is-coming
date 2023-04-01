package com.example.springJPA.exception;

public class DBException extends IllegalArgumentException {

    public DBException(String msg) {
        super(msg);
    }
}
