package com.example;

import com.example.springMVC.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringMVCApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringMVCApplication.class, args);
        SecurityConfig securityConfig = context.getBean(SecurityConfig.class);
        System.out.println(securityConfig.getKey());
    }
}

// 예외 처리, 로그인(JWT)
// ID 자동 생성, Enum -> DB, 복잡한 Entity
// 배포
// 시간 남으면 JPA
