package com.sparta.imageclouds.repository;

import com.sparta.imageclouds.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByKeyword(String keyword);
}
