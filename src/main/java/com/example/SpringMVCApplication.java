package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMVCApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMVCApplication.class, args);
    }
}

// 예외 처리, 로그인(JWT)
// ID 자동 생성, Enum -> DB, 복잡한 Entity
// 배포
// 시간 남으면 JPA
