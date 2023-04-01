package com.example.springJPA.config;

import com.example.springJPA.token.JwtProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider(secret);
    }
}
