package com.example.springMVC.config;

import com.example.springMVC.token.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SecurityConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider(secret);
    }
}
