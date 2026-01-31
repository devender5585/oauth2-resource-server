package com.dev.resource.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;


@RestController
public class TestController {

    @GetMapping("/api/public/hello")
    public String publicApi() {
        return "Public API - No Token Required";
    }

    @GetMapping("/api/secure/hello")
    public String secureApi() {
        return "Secure API - Valid JWT Required";
    }
    
    
    @GetMapping("/api/secure/token-details")
    public Map<String, Object> tokenDetails(
            @AuthenticationPrincipal Jwt jwt) {

        return jwt.getClaims();
    }
    
    @GetMapping("/api/secure/auth-details")
    public Object authDetails(Authentication authentication) {

        JwtAuthenticationToken token =
                (JwtAuthenticationToken) authentication;

        return token.getToken().getClaims();
    }
    
    @GetMapping("/api/secure/client-id")
    public String getClientId(@AuthenticationPrincipal Jwt jwt) {
        return jwt.getClaimAsString("client_id");
    }
    
    @GetMapping("/api/secure/roles")
    public Object getRoles(@AuthenticationPrincipal Jwt jwt) {
        return jwt.getClaimAsStringList("roles");
    }


}
