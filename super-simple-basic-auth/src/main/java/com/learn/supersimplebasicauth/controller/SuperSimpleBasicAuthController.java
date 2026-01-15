package com.learn.supersimplebasicauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/super-simple-basic-auth")
public class SuperSimpleBasicAuthController {

    @GetMapping("/health")
    public ResponseEntity<String> showHealth() {
        return ResponseEntity.ok("Healthy");
    }
}
