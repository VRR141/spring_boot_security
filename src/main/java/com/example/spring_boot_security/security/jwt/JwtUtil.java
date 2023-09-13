package com.example.spring_boot_security.security.jwt;


import com.example.spring_boot_security.security.jwt.impl.JwtClaims;
import org.springframework.security.core.Authentication;

public interface JwtUtil {

    String generateToken(Authentication authentication);

    JwtClaims validateTokenAndRetrieveClaim(String token);
}
