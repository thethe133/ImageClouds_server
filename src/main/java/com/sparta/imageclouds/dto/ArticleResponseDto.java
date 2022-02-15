package com.sparta.imageclouds.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
public class ArticleResponseDto {

    private Long article_id;
    private Long writer_id;
    private String image_url;
    private LocalDateTime created_date;

    public ArticleResponseDto(Long articleId, Long writerId, String imageUrl, LocalDateTime createdDate) {
        this.article_id = articleId;
        this.writer_id = writerId;
        this.image_url = imageUrl;
        this.created_date = createdDate;


    }

}
