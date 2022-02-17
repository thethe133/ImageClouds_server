package com.sparta.week06login.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Setter
@Getter
public class ArticleResponseDto {
    private Long article_id; //게시글 ID
    private Long writer_id; //글쓴이 ID
    private String writer_nickname; //글쓴이 닉네임
    private List<Long> liked_users; //좋아요 누른 사용자 ID 리스트
    private String image_url;
    private List<String> tags; //["귀여워", "멍멍이"]
    private String created_date; //작성 날짜

    public ArticleResponseDto(Long articleId, Long writerId, String writerNickname, List<Long> likedUsers, String imageUrl, List<String> tags, String createdDate) {
        this.article_id = articleId;
        this.writer_id = writerId;
        this.writer_nickname = writerNickname;
        this.liked_users = likedUsers;
        this.image_url = imageUrl;
        this.tags = tags;
        this.created_date = createdDate;
    }

}