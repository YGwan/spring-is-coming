package com.example.springMVC.token;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    public String createToken(Long id, String username, String passwd) {
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("id", id.toString());
        payloads.put("username", username);
        payloads.put("passwd", passwd);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Duration.ofDays(1).toMillis());

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(payloads)
                .setExpiration(expiration) // 만료시간(exp)
                .setSubject("logIn")
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256)) // 알고리즘, 시크릿 키
                .compact();
    }
}
