package com.example.springMVC.controller;

import com.example.springMVC.exception.DBException;
import com.example.springMVC.exception.UserException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PageControllerAdvice {

    // TODO 1 : 사용자 정보가 안맞는 요청이 들어온 경우 적절한 HTTP 응답 코드와 함께 예외 메시지를 사용자에게 출력하세요.
    //          @ExceptionHandler

    // TODO 2 : age가 음수 또는 100이상일 때는 "유효하지 않은 나이입니다"를 사용자에게 반환하고,
    //          age가 19세 미만인 경우 "서비스 정책에 맞지 않는 사용자 나이입니다"를 사용자에게 반환하세요.
    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> handleUpdatePhoneNumber(UserException e) {
        return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleInappropriateRequestData() {
        return new ResponseEntity<>("제대로 입력되지 않은 값이 존재합니다. 다시 확인해주시기 바랍니다.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> npeHandler() {
        return ResponseEntity.badRequest().body("적절하지 않은 요청입니다.");
    }

    @ExceptionHandler(DBException.class)
    public ResponseEntity<?> dbeHandler() {
        return ResponseEntity.badRequest().body("에러가 발생하였습니다.");
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
