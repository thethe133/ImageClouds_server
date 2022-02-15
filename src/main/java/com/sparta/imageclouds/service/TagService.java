package com.sparta.imageclouds.service;

import com.sparta.imageclouds.dto.TagSearchResponseDto;
import com.sparta.imageclouds.model.Article;
import com.sparta.imageclouds.model.ArticleTag;
import com.sparta.imageclouds.model.Likes;
import com.sparta.imageclouds.model.Tag;
import com.sparta.imageclouds.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class TagService {
    private final TagRepository tagRepository;
    private final ArticleTagRepository articleTagRepository;
    private final ArticleRepository articleRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    public List<TagSearchResponseDto> searchTag(String keyword){

        List<TagSearchResponseDto> tagSearchResponseDtoList = new ArrayList<>();

        Tag searchKeywordTag = tagRepository.findByKeyword(keyword);

        List<ArticleTag> articleTags = articleTagRepository.findAllByTag(searchKeywordTag);
        for (ArticleTag articleTag : articleTags) {
            Article article = articleRepository.findById(articleTag.getArticle().getArticleId())
                    .orElseThrow(() -> new RuntimeException("해당  아티클이 없습니다."));

            List<Tag> tags = articleTagRepository.findTagByArticleId(article.getArticleId());
            List<String> tagNames = new ArrayList<>();

            for (Tag tag : tags) {
                tagNames.add(tag.getKeyword());
            }

            List<Long> likeUserIds = new ArrayList<>();
            List<Likes> LikeUser = likeRepository.findAllByArticle(article);

            for (Likes likes : LikeUser) {
                likeUserIds.add(likes.getUser().getUid());
            }
//            User user = userRepository.findByUid();

            // ResponseDto 생성
            TagSearchResponseDto tagSearchResponseDto = new TagSearchResponseDto();
            tagSearchResponseDto.setArticle_id(article.getArticleId());
//            tagSearchResponseDto.setWriter_nickname(user);
            //tagSearchResponseDto.setNickname(article.getUser().getUserNickname());
            tagSearchResponseDto.setImage_url(article.getImageUrl());
            tagSearchResponseDto.setCreated_date(article.getCreatedDate());
            tagSearchResponseDto.setTags(tagNames);
            tagSearchResponseDto.setLiked_users(likeUserIds);
            tagSearchResponseDtoList.add(tagSearchResponseDto);
        }

        return tagSearchResponseDtoList;
    }
}
