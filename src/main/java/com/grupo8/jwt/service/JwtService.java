package com.grupo8.jwt.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.grupo8.jwt.component.KeyProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

    private final KeyProvider keyProvider;

    public JwtService(KeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    public String generateToken(String username, int userId, List<String> roleList) {
        return Jwts.builder()
            .subject(username)
            .issuedAt(Date.from(Instant.now()))
            .expiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
            .claim("userId", userId)
            .claim("roles", roleList)
            .signWith(keyProvider.getPrivateKey(), Jwts.SIG.RS256)
            .compact();
    }

    public Claims validateToken(String token) throws JwtException {
        return Jwts.parser()
            .verifyWith(keyProvider.getPublicKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

}
