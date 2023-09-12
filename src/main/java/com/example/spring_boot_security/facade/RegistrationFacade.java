package com.example.spring_boot_security.facade;

import com.example.spring_boot_security.dto.request.RegistrationDto;
import com.example.spring_boot_security.dto.response.ClientRegistrationResponseDto;

public interface RegistrationFacade {

    ClientRegistrationResponseDto register(RegistrationDto registrationDto);
}
