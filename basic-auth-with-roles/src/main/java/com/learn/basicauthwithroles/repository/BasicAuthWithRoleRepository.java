package com.learn.basicauthwithroles.repository;


import com.learn.basicauthwithroles.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasicAuthWithRoleRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
