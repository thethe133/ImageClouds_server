package com.sparta.imageclouds.service;

import com.sparta.imageclouds.dto.LikeRequestDto;
import com.sparta.imageclouds.model.Likes;
import com.sparta.imageclouds.repository.ArticleRepository;
import com.sparta.imageclouds.repository.LikeRepository;
import com.sparta.imageclouds.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;


    public Boolean getLikes(Long articleId, LikeRequestDto likeRequestDto) {
        Long uid = likeRequestDto.getUid();
        Long article = likeRequestDto.getArticleId();
        List<Likes> articleLike = likeRepository.findAllByArticleId(articleId);
        for (Likes likes : articleLike) {
            Long likeUserId = likes.getUser().getUid();
            if(likeUserId.equals(uid)){
                likeRepository.deleteById(likes.getId());
                return false;
            }
        }
        User user = userRepository.find
        Likes likes = new Likes(user, article);
        likeRepository.save(likes);
        return true;
    }
}
