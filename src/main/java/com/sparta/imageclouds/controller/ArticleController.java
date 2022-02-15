package com.sparta.imageclouds.controller;

import com.sparta.imageclouds.dto.ArticleRequestDto;
import com.sparta.imageclouds.dto.ArticleResponseDto;
import com.sparta.imageclouds.model.Article;
import com.sparta.imageclouds.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;


    // 게시글 작성
    @PostMapping("/pictures")
    public Article createArticle(@RequestBody ArticleRequestDto articleRequestDto) {    // <- 나중에 넣어줘 @AuthenticationPrincipal UserDetailsImpl userDetails
        Article article = articleService.saveArticle(articleRequestDto);
        return article;
    }


    // 게시글 조회
    @GetMapping("/")
    public List<ArticleResponseDto> getArticles() {
        return articleService.getAllArticles();
    }


    // 게시글 상세조회
    @GetMapping("/pictures/{articleId}")
    public ArticleResponseDto detailAticle(@PathVariable Long articleId ) {
        return articleService.getArticle(articleId);
    }


    //게시글 삭제하기
    @DeleteMapping("/pictures/{articleId}")
    public void deleteArticles(@PathVariable Long articleId) {    // <- 나중에 넣어줘 @AuthenticationPrincipal UserDetailsImpl userDetails
        articleService.deleteArticle(articleId);//  <- (articleId, userDetails) 로 고치면될듯?
//        return articleId;
    }


}




