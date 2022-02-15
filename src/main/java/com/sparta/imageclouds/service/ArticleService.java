package com.sparta.imageclouds.service;

import com.sparta.imageclouds.dto.ArticleRequestDto;
import com.sparta.imageclouds.dto.ArticleResponseDto;
import com.sparta.imageclouds.model.Article;
import com.sparta.imageclouds.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;



    //게시글 저장
    @Transactional
    public Article saveArticle(ArticleRequestDto articleRequestDto) {
        Article article = new Article(articleRequestDto);
        articleRepository.save(article);
        return article;
    }


    //게시글 조회
    @Transactional
    public List<ArticleResponseDto> getAllArticles() {
        List<Article> articleList = articleRepository.findAllByOrderByCreatedDateDesc();
        List<ArticleResponseDto> articleResponseDtoList = new ArrayList<>();

        for(Article article : articleList) {
            ArticleResponseDto articleResponseDto = new ArticleResponseDto(
                    article.getArticleId(),
                    article.getWriterId(),
                    article.getImageUrl(),
                    article.getCreatedDate()
            );
            articleResponseDtoList.add(articleResponseDto);
        }
        return articleResponseDtoList;
    }


    //게시글 상세조회
    @Transactional
    public ArticleResponseDto getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(
                ()->new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        ArticleResponseDto articleResponseDto = new ArticleResponseDto(
                article.getArticleId(),
                article.getWriterId(),
                article.getImageUrl(),
                article.getCreatedDate()
        );
        return articleResponseDto;

    }


    //게시글 삭제
    @Transactional
    public void deleteArticle(Long articleId) {  // <-- 넣어야되나 @AuthenticationPrincipal UserDetailsImpl userDetails
        articleRepository.deleteById(articleId);
    }


}
