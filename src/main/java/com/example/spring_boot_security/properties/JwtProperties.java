package com.example.spring_boot_security.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "jwt")
@ConfigurationPropertiesScan("classpath:properties/jwt-properties.yaml")
public class JwtProperties {

    private final String secret;

    private final Integer lifeTime;

    private final String authoritiesHeader;

    private final String clientIdentifierHeader;

    private final String issuer;

    private final String loginHeader;

    private final String authHeader;

    private final String bearerString;

    private final Integer bearerLength;

    @ConstructorBinding
    public JwtProperties(String secret, Integer lifeTime,
                         String authoritiesHeader, String clientIdentifierHeader,
                         String issuer, String loginHeader,
                         String authHeader, String bearerString,
                         Integer bearerLength) {
        this.secret = secret;
        this.lifeTime = lifeTime;
        this.authoritiesHeader = authoritiesHeader;
        this.clientIdentifierHeader = clientIdentifierHeader;
        this.issuer = issuer;
        this.loginHeader = loginHeader;
        this.authHeader = authHeader;
        this.bearerString = bearerString;
        this.bearerLength = bearerLength;
    }
}
