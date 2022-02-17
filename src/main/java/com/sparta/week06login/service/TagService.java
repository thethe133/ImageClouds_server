package com.sparta.week06login.service;


import com.sparta.week06login.dto.ArticleResponseDto;
import com.sparta.week06login.model.Article;
import com.sparta.week06login.model.ArticleTag;
import com.sparta.week06login.model.Likes;
import com.sparta.week06login.model.Tag;
import com.sparta.week06login.repository.ArticleRepository;
import com.sparta.week06login.repository.ArticleTagRepository;
import com.sparta.week06login.repository.LikesRepository;
import com.sparta.week06login.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TagService {
    private final TagRepository tagRepository;
    private final ArticleTagRepository articleTagRepository;
    private final ArticleRepository articleRepository;
    private final LikesRepository likesRepository;

    @Transactional
    public List<ArticleResponseDto> searchTag(String keyword){

        List<ArticleResponseDto> tagSearchResponseDtoList = new ArrayList<>();

        Tag searchKeywordTag = tagRepository.findByKeyword(keyword).get();
        if(searchKeywordTag.equals(null)){ return tagSearchResponseDtoList;} // 태그가 없으면 -> 빈 list return 하기

        List<ArticleTag> articleTags = articleTagRepository.findAllByTag(searchKeywordTag);

        for (ArticleTag articleTag : articleTags) {
            Article article = articleRepository.findById(articleTag.getArticle().getArticleId())
                    .orElseThrow(() -> new RuntimeException("태그는 있지만 해당하는 아티클이 없습니다."));

            List<Tag> tags = articleTagRepository.findTagByArticleId(article.getArticleId());
            List<String> tagNames = new ArrayList<>();

            for (Tag tag : tags) {
                tagNames.add(tag.getKeyword());
            }

            List<Long> likeUserIds = new ArrayList<>();
            List<Likes> LikeUser = likesRepository.findAllByArticle(article);

            for (Likes likes : LikeUser) {
                likeUserIds.add(likes.getLikeUserId());
            }

            // ResponseDto 생성
            ArticleResponseDto tagSearchResponseDto = new ArticleResponseDto();

            tagSearchResponseDto.setArticle_id(article.getArticleId());
            tagSearchResponseDto.setWriter_id(article.getUser().getUid());
            tagSearchResponseDto.setWriter_nickname(article.getUser().getNickname());
            tagSearchResponseDto.setImage_url(article.getImageUrl());
            tagSearchResponseDto.setCreated_date(String.valueOf(article.getCreatedDate()));
            tagSearchResponseDto.setTags(tagNames);
            tagSearchResponseDto.setLiked_users(likeUserIds);

            tagSearchResponseDtoList.add(tagSearchResponseDto);
        }
        return tagSearchResponseDtoList;
    }
}
