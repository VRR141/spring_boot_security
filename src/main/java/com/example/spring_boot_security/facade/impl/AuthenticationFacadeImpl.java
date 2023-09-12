package com.example.spring_boot_security.facade.impl;

import com.example.spring_boot_security.dto.request.AuthenticationRequestDto;
import com.example.spring_boot_security.dto.response.AuthenticationResponseDto;
import com.example.spring_boot_security.facade.AuthenticationFacade;
import com.example.spring_boot_security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    private final AuthenticationService authenticationSerivce;

    @Override
    public AuthenticationResponseDto login(AuthenticationRequestDto dto) {
        String token = authenticationSerivce.login(dto.getLogin(), dto.getPassword());
        return AuthenticationResponseDto.builder()
                .token(token).build();
    }
}
