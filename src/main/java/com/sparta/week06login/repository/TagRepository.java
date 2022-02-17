package com.sparta.week06login.repository;

import com.sparta.week06login.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByKeyword(String eachTag);
}
