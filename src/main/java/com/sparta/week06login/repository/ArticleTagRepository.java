package com.sparta.week06login.repository;

import com.sparta.week06login.model.Article;
import com.sparta.week06login.model.ArticleTag;
import com.sparta.week06login.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleTagRepository extends JpaRepository<ArticleTag, Long> {
    List<ArticleTag> findAllByArticle_ArticleId(Long articleId);
    List<ArticleTag> findAllByTag(Tag tag);

    @Query("select  t.tag " +
            "from ArticleTag t " +
            "where t.article.articleId = :articleId")
    List<Tag> findTagByArticleId(@Param("articleId") Long articleId);

    void deleteAllByArticle_ArticleId(Long articleId);
}
