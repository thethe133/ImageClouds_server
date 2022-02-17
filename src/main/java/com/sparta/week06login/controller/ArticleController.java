package com.sparta.week06login.controller;

import com.sparta.week06login.dto.ArticleRequestDto;
import com.sparta.week06login.dto.ArticleResponseDto;
import com.sparta.week06login.security.UserDetailsImpl;
import com.sparta.week06login.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleController {
    private final ArticleService articleService;

    // 게시글 작성
    @PostMapping("/pictures")
    public String createArticle(@RequestBody ArticleRequestDto articleRequestDto) {
        articleService.saveArticle(articleRequestDto);
        return "게시글 작성 완료!";
    }

    // 게시글 전체 조회
    @GetMapping("/articles")
    public List<ArticleResponseDto> getArticles() {
        return articleService.getAllArticles();
    }

    // 내가 올린 게시글 조회
    @GetMapping("/myarticle")
    public List<ArticleResponseDto> getMyArticles(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return articleService.getMyArticle(userDetails);
    }

    // 게시글 상세조회
    @GetMapping("/detail/{articleId}")
    public ArticleResponseDto detailArticle(@PathVariable Long articleId ) {
        return articleService.detailArticle(articleId);
    }

    // 좋아요 한 게시글 검색
    @GetMapping("/mylike")
    public List<ArticleResponseDto> getMyLikeArticles(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return articleService.getMyLikeArticle(userDetails);
    }


    //게시글 삭제하기
     @DeleteMapping("/pictures/{articleId}")
     public void deleteArticles(@PathVariable Long articleId) {
     articleService.deleteArticle(articleId);
    }


}