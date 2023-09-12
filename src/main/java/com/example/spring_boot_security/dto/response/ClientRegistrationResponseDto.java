package com.example.spring_boot_security.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ClientRegistrationResponseDto {

    @JsonProperty("client_identifier")
    private UUID identifier;
}
