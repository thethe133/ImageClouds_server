package com.sparta.imageclouds.controller;

import com.sparta.imageclouds.dto.LikeRequestDto;
import com.sparta.imageclouds.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/pictures/{articleId}/like")
    public boolean getLikes(@PathVariable Long articleId, LikeRequestDto likeRequestDto){
//        LikeResponseDto likeResponseDto = new LikeResponseDto();
        boolean isLike;
        isLike = likeService.getLikes(articleId, likeRequestDto);
        return isLike;

    }
}

