package com.sparta.week06login.repository;

import com.sparta.week06login.model.Article;
import com.sparta.week06login.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByArticleAndLikeUserId(Article article, Long likeUserId);
    Long deleteByLikeId(long likeId);
    List<Likes> findAllByArticle_ArticleId(Long articleId);
    List<Likes> findAllByArticle(Article article);
    void deleteAllByArticle_ArticleId(long articleId);
    List<Likes> findAllByLikeUserId(Long uid);
}
