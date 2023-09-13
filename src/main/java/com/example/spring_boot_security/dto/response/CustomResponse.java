package com.example.spring_boot_security.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CustomResponse {

    @JsonProperty("time_stamp")
    private Instant instant = Instant.now();

    @JsonProperty("message")
    private String message;
}
