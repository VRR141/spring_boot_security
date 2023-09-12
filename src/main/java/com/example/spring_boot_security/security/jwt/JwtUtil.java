package com.example.spring_boot_security.security.jwt;


import org.springframework.security.core.Authentication;

public interface JwtUtil {

    String generateToken(Authentication authentication);

    JwtClaims validateTokenAndRetrieveClaim(String token);
}
