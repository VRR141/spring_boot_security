package com.example.spring_boot_security.security;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

@ToString
@Getter
public class AuthorizedUser extends User {

    private final UUID clientIdentifier;

    public AuthorizedUser(String username, String password,
                          boolean enabled, Collection<? extends GrantedAuthority> authorities, UUID clientIdentifier) {
        super(username, password, enabled,
                true, true,
                true, authorities);
        this.clientIdentifier = clientIdentifier;
    }
}
