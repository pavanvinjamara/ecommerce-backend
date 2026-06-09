package com.example.backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${spring.jwt.secret}")
    private String secret;

    @Value("${spring.jwt.expiration}")
    private long expiration;

    // Generate Key
    private Key getKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Generate Token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ FIXED
    public String extractEmail(String token){
        return Jwts.parser()
                .setSigningKey(getKey())
                .parseClaimsJws(token)   // 🔥 FIX HERE
                .getBody()
                .getSubject();
    }

    // ✅ FIXED
    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .setSigningKey(getKey())
                    .parseClaimsJws(token)   // 🔥 FIX HERE
                    .getBody();
            return true;
        } catch (JwtException e){
            return false;
        }
    }
}