package com.sparta.week06login.repository;

import com.sparta.week06login.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByOrderByCreatedDateDesc();
    Optional<Article> findByArticleId(Long articleId);
    void deleteByArticleId(Long articleId);
    List<Article> findAllByUser_Uid(Long uid);
}
