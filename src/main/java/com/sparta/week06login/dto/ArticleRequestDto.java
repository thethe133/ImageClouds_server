package com.sparta.week06login.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

public class ArticleRequestDto {
    private final Long uid;
    private final String imageUrl;
    private final List<String> tags;

    public ArticleRequestDto(Long uid, String image_url, List<String> tags){
        this.uid = uid;
        this.imageUrl = image_url;
        this.tags = tags;
    }
}