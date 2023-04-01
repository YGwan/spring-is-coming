package com.example.springJPA.controller;

import com.example.springJPA.dto.*;
import com.example.springJPA.exception.AuthException;
import com.example.springJPA.service.UserService;
import com.example.springJPA.exception.UserException;
import com.example.springJPA.token.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public UserController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PutMapping("/phoneNumber")
    public ResponseEntity<UpdatePhoneNumberResponse> updatePhoneNumber(@RequestHeader("Authorization") String token, @RequestBody UpdatePhoneNumberRequest request) {
        validateToken(request.getId(), token);
        return ResponseEntity.ok(userService.updatePhoneNumberByNameAndAge(request));
    }

    @PutMapping("/age")
    public ResponseEntity<UpdateAgeResponse> updateAge(@RequestHeader("Authorization") String token, @RequestBody UpdateAgeRequest request) {
        validateToken(request.getId(), token);
        return ResponseEntity.ok(userService.updateAgeById(request));
    }

    private void validateToken(Long id, String token) {
        if (!jwtProvider.validToken(token)) {
            throw new AuthException("권한 없음");
        }
        if (!(jwtProvider.getUsernameByToken(token).equals(userService.getUsername(id)))) {
            throw new AuthException("권한 없음");
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest request) {
        String jwtToken = jwtProvider.createTokenByUsername(request.getUsername());
        return ResponseEntity.ok(userService.signUp(request) + " " + jwtToken);
    }

    @PostMapping("/logIn")
    public ResponseEntity<String> logIn(@RequestBody LogInRequest request) {
        userService.logIn(request);
        String jwtToken = jwtProvider.createTokenByUsername(request.getUsername());
        return ResponseEntity.ok(jwtToken);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> manvException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> userExceptionHandler(UserException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> authExceptionHandler(AuthException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
