package com.example.spring_boot_security.exception.conflict;

import com.example.spring_boot_security.exception.ConflictException;

public class ClientLoginAlreadyExistException extends ConflictException {

    private static final String message = "Client with login %s already exist in system";

    public ClientLoginAlreadyExistException(String login) {
        super(String.format(message, login));
    }
}
