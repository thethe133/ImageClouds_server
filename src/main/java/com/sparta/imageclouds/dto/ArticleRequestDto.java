package com.sparta.imageclouds.dto;
import lombok.*;

@Setter
@Getter
public class ArticleRequestDto {

    private Long writerId;
    private String imageUrl;

    public ArticleRequestDto(Long writer_id, String image_url) {

        this.writerId = writer_id;
        this.imageUrl = image_url;

    }



}