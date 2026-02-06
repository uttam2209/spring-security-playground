package com.learn.customauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/custom-auth")
public class CustomAuthController {

    @GetMapping("/demo")
    public ResponseEntity<String> demo() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok("Hello");
    }
}
