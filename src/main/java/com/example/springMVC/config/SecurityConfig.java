package com.example.springMVC.config;

import com.example.springMVC.token.JwtProvider;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

@Configuration
public class SecurityConfig {

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Bean
    public JwtProvider jwtProvider() {
        return new JwtProvider(key);
    }

    public String getKey() {
        return new String(key.getEncoded());
    }
}
