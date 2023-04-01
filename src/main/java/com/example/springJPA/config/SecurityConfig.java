package com.example.springJPA.config;

import com.example.springJPA.data.Data;
import com.example.springJPA.token.JwtProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class SecurityConfig {

    private static final SecretKey secretKey = Data.getSecretKey();

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider(secretKey);
    }
}
