package com.example.springhello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class My2Controller {

    int count = 0;

    // 5. My2Controller를 정의하고 방문 횟수를 Count하여 출력하는 메서드를 정의하세요.
    @GetMapping("/count")
    public String count() {
        count++;
        return  String.valueOf(count);
    }
}
