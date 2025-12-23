package com.learn.basicauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/basic-auth")
public class SecurityPlayController {

    @GetMapping("/health")
    public ResponseEntity<String> showHealth() {
        return ResponseEntity.ok("Healthy");
    }
}
