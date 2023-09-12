package com.example.spring_boot_security.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class JwtClaims {

    private List<? extends GrantedAuthority> authorities;

    private String clientIdentifier;
}
