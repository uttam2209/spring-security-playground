package com.learn.basicauthwithroles.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/basic-auth-with-role")
public class BasicAuthWithRole {

    @GetMapping("/admin")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("Only admin");
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<String> adminOrUserOnly() {
        return ResponseEntity.ok("Admin or user");
    }

    /**
     * while calling this endpoint, dont send any authentication details at all.
     * if you send lets say basic auth, then the securityFilterChain will see if its valid.
     */
    @GetMapping("/public")
    public ResponseEntity<String> publicAccess() {
        return ResponseEntity.ok("Public access");
    }
}
