package com.example.springMVC.controller;

import com.example.springMVC.dto.AuthUserRequest;
import com.example.springMVC.dto.SignUpRequest;
import com.example.springMVC.dto.UserResponse;
import com.example.springMVC.service.UserService;
import com.example.springMVC.token.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public AdminController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> userInfo(@PathVariable Long id) {
        UserResponse response = userService.getUser(id);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> emailHandler() {
        return new ResponseEntity<>("올바른 형식의 이메일 주소여야 합니다.", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> userAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest request) {
        String jwtToken = jwtProvider.createToken(request.getUsername(), request.getEmail());
        return ResponseEntity.ok(userService.signUp(request) + " " + jwtToken);
    }

    @PostMapping("/auth")
    public ResponseEntity<String> authUser(@RequestBody AuthUserRequest request) {
        return ResponseEntity.ok(jwtProvider.validateToken(request.getToken()));
    }
}
