package com.example.springDataJDBC.controller;

import com.example.springDataJDBC.entity.User;
import com.example.springDataJDBC.service.JdbcTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private JdbcTemplateService jdbcTemplateService;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> userInfo(@PathVariable Long id) {
        return ResponseEntity.ok(jdbcTemplateService.getUser(id));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<String> usersInfo(@PathVariable Long id) {
        List<User> users = jdbcTemplateService.getUsers(id);
        StringBuilder output = new StringBuilder();
        for (User user : users) {
            output.append("id : ").append(user.getId());
            output.append(", name : ").append(user.getName());
            output.append(", age : ").append(user.getAge());
        }
        return ResponseEntity.ok(output.toString());
    }

    @PostMapping("/user")
    public ResponseEntity<Long> userAdd(@RequestBody User user) {
        // user 정보를 request body에 담고 이를 받아 db에 넣으세요.
        // 리턴은 유저 아이디를 반환하세요.
        return ResponseEntity.ok(jdbcTemplateService.insertUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> userAll() {
        // 모든 유저를 반환하세요.
        return ResponseEntity.ok(jdbcTemplateService.getAllUsers());
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        // 유저 정보를 request body에 담고 해당 id의 유저 정보를 수정하세요.
        return ResponseEntity.ok(jdbcTemplateService.updateUserByID(user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(jdbcTemplateService.deleteUser(id));
    }
}
