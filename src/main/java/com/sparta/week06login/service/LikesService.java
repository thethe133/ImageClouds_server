package com.sparta.week06login.service;

import com.sparta.week06login.dto.LikeRequestDto;
import com.sparta.week06login.model.Article;
import com.sparta.week06login.model.Likes;
import com.sparta.week06login.repository.ArticleRepository;
import com.sparta.week06login.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service


public class LikesService {
    private final LikesRepository likesRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public Boolean getLikes(LikeRequestDto likeRequestDto){
        Long uid = likeRequestDto.getUid();
        Long articleId = likeRequestDto.getArticleId();
        System.out.println(articleId);
        Optional<Article> articletemp = articleRepository.findByArticleId(articleId);
        System.out.println(articletemp);
        Article article = articletemp.get();

        Optional<Likes> found = likesRepository.findByArticleAndLikeUserId(article, uid);

        if(found.isPresent()){
            likesRepository.deleteByLikeId(found.get().getLikeId());
            return false;
        } else {
            Likes likes = new Likes(article, uid);
            likesRepository.save(likes);
            return true;
        }
    }


}