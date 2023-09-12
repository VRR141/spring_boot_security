package com.example.spring_boot_security.facade.impl;

import com.example.spring_boot_security.domain.Client;
import com.example.spring_boot_security.dto.request.RegistrationDto;
import com.example.spring_boot_security.dto.response.ClientRegistrationResponseDto;
import com.example.spring_boot_security.facade.RegistrationFacade;
import com.example.spring_boot_security.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RegistrationFacadeImpl implements RegistrationFacade {

    private final RegistrationService registrationService;

    @Override
    public ClientRegistrationResponseDto register(RegistrationDto registrationDto) {
        Client client = Client.builder()
                .login(registrationDto.getLogin())
                .password(registrationDto.getPassword())
                .build();
        client = registrationService.register(client);
        return ClientRegistrationResponseDto.builder()
                .identifier(client.getIdentifier())
                .build();
    }
}
