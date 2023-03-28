package com.example.springMVC.config;

import com.example.springMVC.data.Data;
import com.example.springMVC.token.JwtProvider;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Configuration
public class SecurityConfig {

    private static final String secretString = Data.getSecretString();
    private static final SecretKey secretKey = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider(secretKey);
    }

    public String getKey() {
        return secretString;
    }
}
