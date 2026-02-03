package com.dev.resource.server.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class SecureDataController {

    @GetMapping("/user-profile")
    public Map<String, Object> getUserProfile() {
        Map<String, Object> data = new HashMap<>();
        data.put("username", "Devender Sharma");
        data.put("role", "Java Developer");
        data.put("status", "Active");
        data.put("lastLogin", "2026-02-02");
        
        return data;
    }
}
