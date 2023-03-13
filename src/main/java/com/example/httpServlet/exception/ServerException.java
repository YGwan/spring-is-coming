package com.example.httpServlet.exception;

public class ServerException extends IllegalArgumentException {

    public ServerException(String msg) {
        super(msg);
    }
}
