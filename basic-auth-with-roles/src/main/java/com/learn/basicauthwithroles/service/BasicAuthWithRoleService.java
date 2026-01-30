package com.learn.basicauthwithroles.service;

import com.learn.basicauthwithroles.repository.BasicAuthWithRoleRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BasicAuthWithRoleService implements UserDetailsService {
    private final BasicAuthWithRoleRepository basicAuthWithRoleRepository;

    public BasicAuthWithRoleService(BasicAuthWithRoleRepository basicAuthWithRoleRepository) {
        this.basicAuthWithRoleRepository = basicAuthWithRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return basicAuthWithRoleRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
