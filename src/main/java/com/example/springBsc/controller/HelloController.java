package com.example.springBsc.controller;

import com.example.springBsc.entity.Hello;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("ex")
    public String ex(Model mode) {
        mode.addAttribute("data", "hello!!");
        return "ex";
    }

    @GetMapping("ex-mvc")
    public String exMVC(@RequestParam("hello") String hello, Model model) {
        model.addAttribute("hello", hello);
        return "thymeleaf_sample";
    }

    @GetMapping("ex-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("hello") String hello) {
        Hello hello1 = new Hello();
        hello1.setName(hello);
        return hello1;
    }
}
