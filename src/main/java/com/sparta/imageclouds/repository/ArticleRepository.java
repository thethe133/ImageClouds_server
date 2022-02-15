package com.sparta.imageclouds.repository;


import com.sparta.imageclouds.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findAllByOrderByCreatedDateDesc();
}