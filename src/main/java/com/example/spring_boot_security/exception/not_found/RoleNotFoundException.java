package com.example.spring_boot_security.exception.not_found;

import com.example.spring_boot_security.exception.NotFoundException;

public class RoleNotFoundException extends NotFoundException {

    private static final String message = "Role with name %s not found";

    public RoleNotFoundException(String name) {
        super(String.format(message, name));
    }
}
