package com.leonardo.taskmanager.security.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {
    
    private String secretKey;
    private String tokenPrefix;
    private String tokenExpirationAfterDays;
    private String authorizationHeaderName;

}
