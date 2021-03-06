package com.sparta.imageclouds.repository;

import com.sparta.imageclouds.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User findByUid(Long uid);
}