package com.example.spring_boot_security.service.impl;

import com.example.spring_boot_security.security.jwt.JwtUtil;
import com.example.spring_boot_security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @Override
    public String login(String login, String password) {
        UsernamePasswordAuthenticationToken authToken
                = new UsernamePasswordAuthenticationToken(login, password);
        Authentication authenticate = authenticationManager.authenticate(authToken);
        String token = jwtUtil.generateToken(authenticate);
        log.info("Token successfully generated for client {}", login);
        return token;
    }
}
