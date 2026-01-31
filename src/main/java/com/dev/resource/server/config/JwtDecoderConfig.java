package com.dev.resource.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class JwtDecoderConfig {

	@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
	private String issuer;
	
	@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
	private String jwk_set_uri;
	
    @Bean
    public JwtDecoder jwtDecoder() {

        NimbusJwtDecoder decoder = NimbusJwtDecoder
                .withJwkSetUri(jwk_set_uri)
                .build();

        OAuth2TokenValidator<Jwt> issuerValidator =
                JwtValidators.createDefaultWithIssuer(issuer);

        OAuth2TokenValidator<Jwt> audienceValidator =
                new AudienceValidator("resource-server");

        OAuth2TokenValidator<Jwt> combined =
                new DelegatingOAuth2TokenValidator<>(
                        issuerValidator,
                        audienceValidator
                );

        decoder.setJwtValidator(combined);

        return decoder;
    }
}
