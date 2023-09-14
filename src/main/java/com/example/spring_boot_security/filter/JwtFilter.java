package com.example.spring_boot_security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.spring_boot_security.properties.JwtProperties;
import com.example.spring_boot_security.security.jwt.JwtUtil;
import com.example.spring_boot_security.security.jwt.impl.JwtClaims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProperties jwtProperties;

    private final JwtUtil jwtUtil;

    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authHeader = request.getHeader(jwtProperties.getAuthHeader());
            if (StringUtils.hasText(authHeader) && authHeader.startsWith(jwtProperties.getBearerString())) {
                String jwt = authHeader.substring(jwtProperties.getBearerLength());

                JwtClaims claims = jwtUtil.validateTokenAndRetrieveClaim(jwt);

                Authentication authentication = new PreAuthenticatedAuthenticationToken(
                        claims.getClientIdentifier(), null, claims.getAuthorities()
                );

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException ex) {
            handlerExceptionResolver.resolveException(request, response, null, ex);
        }
    }
}
