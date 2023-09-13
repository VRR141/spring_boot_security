package com.example.spring_boot_security.handler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.spring_boot_security.dto.response.CustomResponse;
import com.example.spring_boot_security.exception.ConflictException;
import com.example.spring_boot_security.exception.NotFoundException;
import com.example.spring_boot_security.exception.SecurityServiceCustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    ResponseEntity<CustomResponse> handleNotFoundException(NotFoundException ex) {
        log.warn(ex.getMessage());
        log.trace(ex.getMessage(), ex);
        return ResponseEntity.status(404).body(handle(ex));
    }

    @ExceptionHandler({ConflictException.class})
    ResponseEntity<CustomResponse> handleConflictException(ConflictException ex) {
        log.warn(ex.getMessage());
        log.trace(ex.getMessage(), ex);
        return ResponseEntity.status(409).body(handle(ex));
    }

    @ExceptionHandler({BadCredentialsException.class,
            AccountExpiredException.class,
            DisabledException.class,
            InsufficientAuthenticationException.class,
            CredentialsExpiredException.class,
            AccountStatusException.class})
    ResponseEntity<CustomResponse> handleUnauthorizedException(Exception ex) {
        log.warn(ex.getMessage());
        log.trace(ex.getMessage(), ex);
        return ResponseEntity.status(401).body(handle(ex));
    }

    @ExceptionHandler({AccessDeniedException.class, JWTVerificationException.class})
    ResponseEntity<CustomResponse> handleUForbiddenException(Exception ex) {
        log.warn(ex.getMessage());
        log.trace(ex.getMessage(), ex);
        return ResponseEntity.status(403).body(handle(ex));
    }

    @ExceptionHandler(SecurityServiceCustomException.class)
    ResponseEntity<CustomResponse> handleServiceException(SecurityServiceCustomException ex) {
        log.warn(ex.getMessage());
        log.trace(ex.getMessage(), ex);
        return ResponseEntity.status(500).body(handle(ex));
    }

    private CustomResponse handle(Exception ex) {
        return CustomResponse.builder()
                .message(ex.getMessage())
                .instant(Instant.now())
                .build();
    }
}
