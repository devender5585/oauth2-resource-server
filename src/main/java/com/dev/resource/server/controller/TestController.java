package com.dev.resource.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
