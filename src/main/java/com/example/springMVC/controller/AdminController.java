package com.example.springMVC.controller;

import com.example.springMVC.dto.UserResponse;
import com.example.springMVC.entity.User;
import com.example.springMVC.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> userInfo(@PathVariable Long id) {
        UserResponse response = userService.getUser(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user")
    public ResponseEntity<Long> userAdd(@RequestBody User user) {
        return ResponseEntity.ok(userService.insertUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> userAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/user")
    public ResponseEntity<UserResponse> updateUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.updateUserById(user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
