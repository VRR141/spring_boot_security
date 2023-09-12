package com.example.spring_boot_security.rest;

import com.example.spring_boot_security.dto.request.AuthenticationRequestDto;
import com.example.spring_boot_security.dto.response.AuthenticationResponseDto;
import com.example.spring_boot_security.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationFacade authenticationFacade;

    @PostMapping("/login")
    ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        log.info("Accepted request for login for user {}", authenticationRequestDto.getLogin());
        AuthenticationResponseDto token =
                authenticationFacade.login(authenticationRequestDto);
        log.info("Request for login client {} successfully passed", authenticationRequestDto.getLogin());
        return ResponseEntity.ok(token);
    }
}
