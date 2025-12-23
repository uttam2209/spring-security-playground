package com.learn.jwtauth.repository;

import com.learn.jwtauth.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtAuthRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
