package com.example.spring_boot_security.facade;

import com.example.spring_boot_security.dto.request.AuthenticationRequestDto;
import com.example.spring_boot_security.dto.response.AuthenticationResponseDto;

public interface AuthenticationFacade {

    AuthenticationResponseDto login(AuthenticationRequestDto dto);
}
