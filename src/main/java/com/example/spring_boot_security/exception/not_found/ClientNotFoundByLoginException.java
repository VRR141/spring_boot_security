package com.example.spring_boot_security.exception.not_found;

import com.example.spring_boot_security.exception.NotFoundException;

public class ClientNotFoundByLoginException extends NotFoundException {

    private static final String message = "Client not found by login %s";

    public ClientNotFoundByLoginException(String login) {
        super(String.format(message, login));
    }
}
