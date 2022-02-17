package com.sparta.week06login.controller;

import com.sparta.week06login.dto.LikeRequestDto;
import com.sparta.week06login.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class LikesController {
    private final LikesService likesService;

    @PostMapping("/pictures/like")
    public boolean getLikes(@RequestBody LikeRequestDto likeRequestDto){
        boolean isLike = likesService.getLikes(likeRequestDto);
        return isLike;
    }

}