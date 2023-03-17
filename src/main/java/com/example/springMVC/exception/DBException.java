package com.example.springMVC.exception;

public class DBException extends IllegalArgumentException{

    public DBException(String msg) {
        super(msg);
    }
}
