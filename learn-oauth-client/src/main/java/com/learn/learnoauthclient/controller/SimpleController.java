package com.learn.learnoauthclient.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/oauth-client")
public class SimpleController {

    @GetMapping("/default")
    public ResponseEntity<String> defaultClient() {
        return ResponseEntity.ok("Default");
    }

    @GetMapping("/token-details")
    public ResponseEntity<String> tokenDetails(OAuth2AuthenticationToken  token) {
        return ResponseEntity.ok(token.getPrincipal().getAttributes().toString());
    }

    @GetMapping("/user-details")
    public ResponseEntity<String> userDetails() {
        return ResponseEntity.ok("hello");
    }
}
