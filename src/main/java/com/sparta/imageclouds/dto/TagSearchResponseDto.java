package com.sparta.imageclouds.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TagSearchResponseDto {

    private Long article_id;
    private String writer_nickname;
    private String nickname;
    private String image_url;
    private LocalDateTime created_date;

    private List<Long> liked_users;

    private List<String> tags;
}
