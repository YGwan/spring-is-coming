package com.example.springMVC.token;

import com.example.springMVC.exception.AuthException;
import io.jsonwebtoken.*;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtProvider {

    private final Key key;

    public JwtProvider(Key key) {
        this.key = key;
    }

    public String createTokenByUsername(String username) {
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("username", username);
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

    public boolean validToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token).getBody();
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new AuthException("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            throw new AuthException("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new AuthException("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new AuthException("JWT 토큰이 잘못되었습니다.");
        }
    }
}
