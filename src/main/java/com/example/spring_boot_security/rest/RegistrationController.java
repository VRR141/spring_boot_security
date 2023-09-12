package com.example.spring_boot_security.rest;

import com.example.spring_boot_security.dto.request.RegistrationDto;
import com.example.spring_boot_security.dto.response.ClientRegistrationResponseDto;
import com.example.spring_boot_security.facade.RegistrationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {

    private final RegistrationFacade registrationFacade;

    @PostMapping("/register")
    ResponseEntity<ClientRegistrationResponseDto> registerClient(@RequestBody RegistrationDto registrationDto) {
        log.info("Accepted registration request for client {}",
                registrationDto.getLogin());
        ClientRegistrationResponseDto responseDto
                = registrationFacade.register(registrationDto);
        log.info("Client with login {} and identifier {} successfully registered",
                registrationDto.getLogin(), responseDto.getIdentifier());
        return ResponseEntity.status(201).body(responseDto);
    }

}
