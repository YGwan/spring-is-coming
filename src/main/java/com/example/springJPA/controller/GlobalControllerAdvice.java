package com.example.springJPA.controller;

import com.example.springJPA.exception.DBException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleInappropriateRequestData() {
        return new ResponseEntity<>("제대로 입력되지 않은 값이 존재합니다. 다시 확인해주시기 바랍니다.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> unhandledExceptionHandler(IllegalArgumentException e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().body("죄송합니다 서버 개발의 문제입니다.");
    }

    @ExceptionHandler(DBException.class)
    public ResponseEntity<?> dbeHandler() {
        return ResponseEntity.badRequest().body("DB 에러가 발생하였습니다.");
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?> erdaHandler() {
        return ResponseEntity.badRequest().body("조회 결과가 존재하지 않습니다.");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> hrmnsHandler() {
        return ResponseEntity.badRequest().body("적절한 형식의 요청 타입이 아닙니다.");
    }
}
