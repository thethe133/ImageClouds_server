package com.sparta.week06login.repository;

import com.sparta.week06login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUid(Long uid);

    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmail(String email);
}