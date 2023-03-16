package com.example.springMVC.config;

import com.example.springMVC.token.JwtProvider;
import org.springframework.context.annotation.Bean;

public class SecurityConfig {

    @Bean
    public JwtProvider jwtTokenProvider() {
        return new JwtProvider();
    }
}
