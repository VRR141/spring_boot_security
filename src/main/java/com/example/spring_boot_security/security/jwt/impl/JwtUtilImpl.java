package com.example.spring_boot_security.security.jwt.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.spring_boot_security.aop.ExecutionTime;
import com.example.spring_boot_security.domain.Authorities;
import com.example.spring_boot_security.properties.JwtProperties;
import com.example.spring_boot_security.security.AuthorizedUser;
import com.example.spring_boot_security.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class JwtUtilImpl implements JwtUtil {

    private final JwtProperties jwtProperties;

    @Override
    @ExecutionTime(timeUnit = TimeUnit.NANOSECONDS)
    public JwtClaims validateTokenAndRetrieveClaim(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtProperties.getSecret()))
                .withIssuer(jwtProperties.getIssuer())
                .build();
        DecodedJWT jwt = verifier.verify(token);
        String identifier = jwt.getClaim(jwtProperties.getClientIdentifierHeader()).asString();
        List<Authorities> list
                = jwt.getClaim(jwtProperties.getAuthoritiesHeader()).asList(String.class)
                .stream().map(s -> {
                    Authorities authorities = new Authorities();
                    authorities.setAuthority(s);
                    return authorities;
                }).toList();
        return JwtClaims.builder()
                .authorities(list)
                .clientIdentifier(identifier)
                .build();
    }

    @Override
    @ExecutionTime
    public String generateToken(Authentication authentication) {
        Instant expiration = ZonedDateTime.now()
                .plusMinutes(jwtProperties.getLifeTime()).toInstant();
        AuthorizedUser principal = (AuthorizedUser) authentication.getPrincipal();
        return JWT.create()
                .withClaim(jwtProperties.getClientIdentifierHeader(), principal.getClientIdentifier().toString())
                .withClaim(jwtProperties.getAuthoritiesHeader(), authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .withIssuedAt(Instant.now())
                .withIssuer(jwtProperties.getIssuer())
                .withExpiresAt(expiration)
                .sign(Algorithm.HMAC256(jwtProperties.getSecret()));
    }
}
