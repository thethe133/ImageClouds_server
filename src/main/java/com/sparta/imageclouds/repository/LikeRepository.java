package com.sparta.imageclouds.repository;

import com.sparta.imageclouds.model.Article;
import com.sparta.imageclouds.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    List<Likes> findAllByArticle(Article article);

    List<Likes> findAllByArticleId(Long articleId);
}
