package com.example.springJPA.data;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class Data {

    private static final String secretString = "StartingWithTheSpringFrameworkOfBackEndDevelopers";

    public static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
    }
}
