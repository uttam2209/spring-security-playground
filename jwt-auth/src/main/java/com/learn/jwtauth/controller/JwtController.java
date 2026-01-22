package com.learn.jwtauth.controller;

import com.learn.jwtauth.model.JwtAuthRequest;
import com.learn.jwtauth.util.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/jwt-auth")
public class JwtController {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public JwtController(AuthenticationManager authenticationManager,  JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> jwtAuthenticate(@RequestBody JwtAuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        String jwtToken = jwtUtil.generateJwtToken(request.username());
        return ResponseEntity.ok(jwtToken);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("HEALTHY!");
    }
}
