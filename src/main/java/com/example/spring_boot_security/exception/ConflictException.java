package com.example.spring_boot_security.exception;

public class ConflictException extends SecurityServiceCustomException {

    public ConflictException(String message) {
        super(message);
    }
}
