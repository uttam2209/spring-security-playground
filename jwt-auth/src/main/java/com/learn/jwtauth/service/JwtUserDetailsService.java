package com.learn.jwtauth.service;

import com.learn.jwtauth.repository.JwtAuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final JwtAuthRepository jwtAuthRepository;

    public JwtUserDetailsService(JwtAuthRepository jwtAuthRepository) {
        this.jwtAuthRepository = jwtAuthRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return jwtAuthRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("JWT auth unsuccessful - Username not found"));
    }
}
