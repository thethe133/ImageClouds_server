package com.sparta.imageclouds.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeRequestDto {
    private Long articleId;
    private Long uid;
    private boolean isLike;

//    public LikeRequestDto(Long article_id, Long uid){
//        this.articleId = article_id;
//        this.uid = uid;
//    }
}
