package com.example.springMVC.token;

import com.example.springMVC.exception.UserException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String createToken(String username, String email) {
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("username", username);
        payloads.put("email", email);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Duration.ofDays(1).toMillis());
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(payloads)
                .setExpiration(expiration)
                .setSubject("user-auto")
                .signWith(key)
                .compact();
    }

    public String validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody();
            return "올바른 토큰입니다.";
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new UserException("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            throw new UserException("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new UserException("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new UserException("JWT 토큰이 잘못되었습니다.");
        }
    }
}
