package com.example.spring_boot_security.exception;

public class SecurityServiceCustomException extends RuntimeException {

    public SecurityServiceCustomException() {
    }

    public SecurityServiceCustomException(String message) {
        super(message);
    }

    public SecurityServiceCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityServiceCustomException(Throwable cause) {
        super(cause);
    }

    public SecurityServiceCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
