package com.sparta.week06login.service;

import com.sparta.week06login.dto.ArticleRequestDto;
import com.sparta.week06login.dto.ArticleResponseDto;
import com.sparta.week06login.model.*;
import com.sparta.week06login.repository.*;
import com.sparta.week06login.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ArticleTagRepository articleTagRepository;
    private final TagRepository tagRepository;
    private final LikesRepository likesRepository;


    //게시글 저장
    @Transactional
    public void saveArticle(ArticleRequestDto articleRequestDto) {
        Long uid = articleRequestDto.getUid();
        String imageUrl = articleRequestDto.getImageUrl();
        List<String> tags = articleRequestDto.getTags();

        //uid로 User 객체 가져오기
        Optional<User> userTemp = userRepository.findByUid(uid);
        User user = userTemp.get();

        Article article = new Article(user, imageUrl);

        //방금 article table에 저장한 아티클 id를 가져옴
        Long freshArticleId = articleRepository.save(article).getArticleId();
        Optional<Article> article1 = articleRepository.findById(freshArticleId);

        for(String eachTag : tags){
            Optional<Tag> found = tagRepository.findByKeyword(eachTag);
            //이미 태그가 존재할 경우
            if(found.isPresent()){
                Tag tag = found.get();
                ArticleTag articleTag = new ArticleTag(article1.get(), tag);
                articleTagRepository.save(articleTag);

            } else {
                //태그에 새롭게 등록하고 save한 태그 객체를 가져온다
                Tag newtag = new Tag(eachTag);
                Tag tag = tagRepository.save(newtag);
                ArticleTag articleTag = new ArticleTag(article1.get(), tag);
                articleTagRepository.save(articleTag);
            }
        }
    }


    //게시글 전체 조회
    @Transactional
    public List<ArticleResponseDto> getAllArticles(){
        //반환할 리스트
        List<ArticleResponseDto> responseDtoList = new ArrayList<>();

        //아티클 전체를 불러와 리스트에 저장한다
        List<Article> articleList = articleRepository.findAllByOrderByCreatedDateDesc();

        //for문을 돌며 각 아티클을 articleResponseDto 형식으로 만들어주고
        for(Article eachArticle : articleList){
            Long articleId = eachArticle.getArticleId();

            User user = eachArticle.getUser();
            Long writerId = user.getUid();
            String writerNickname = user.getNickname();

            String imageUrl = eachArticle.getImageUrl();

            //내 아티클 아이디에 해당하는 아티클태그 객체 값들을 모두 불러왔음
            // [{articletag_id : 100, article_id : 2, tag_id : 3},{articletag_id : 101, article_id : 2, tag_id : 5}]
            List<ArticleTag> articleTags = articleTagRepository.findAllByArticle_ArticleId(articleId);

            // articleTags 포문을 돌면서 tag_id를 꺼내고, tagRepository에서 tag name을 꺼내서 tags 리스트에 하나씩 넣어준다
            List<String> tags = new ArrayList<>();

            for(ArticleTag eachArticleTags : articleTags){
                Long tagId = eachArticleTags.getTag().getTagId();
                //없으면 에러내야하는데 그냥 안함
                Optional<Tag> tagKeywordtemp = tagRepository.findById(tagId);
                String tagKeyword = tagKeywordtemp.get().getKeyword();
                tags.add(tagKeyword);
            }

            List<Long> likedUsers = new ArrayList<>();
            List<Likes> likesList = likesRepository.findAllByArticle_ArticleId(articleId);
            for (Likes eachLikes: likesList){
                Long likeUserId = eachLikes.getLikeUserId();
                likedUsers.add(likeUserId);
            }

            String createdDate = String.valueOf(eachArticle.getCreatedDate());

            ArticleResponseDto articleResponseDto = new ArticleResponseDto(articleId, writerId, writerNickname, likedUsers, imageUrl,tags, createdDate);

            //반환할 리스트에 하나씩 넣어준다
            responseDtoList.add(articleResponseDto);
        }

        //리스트를 돌려준다
        return responseDtoList;
    }

    //게시글 삭제
    @Transactional
     public void deleteArticle(Long articleId) {
        articleTagRepository.deleteAllByArticle_ArticleId(articleId);
        likesRepository.deleteAllByArticle_ArticleId(articleId);
        articleRepository.deleteById(articleId);
    }

    //게시글 상세조회
    @Transactional
    public ArticleResponseDto detailArticle(Long articleId) {

        Article article = articleRepository.findById(articleId).orElseThrow(
                ()->new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );

        String writerNickname = article.getUser().getNickname();
        String imageUrl = article.getImageUrl();
        Long writerId = article.getUser().getUid();

        List<ArticleTag> articleTags = articleTagRepository.findAllByArticle_ArticleId(articleId);

        List<String> tags = new ArrayList<>();

        for(ArticleTag eachArticleTags : articleTags){
            Long tagId = eachArticleTags.getTag().getTagId();
            //없으면 에러내야하는데 그냥 안함
            Optional<Tag> tagKeywordtemp = tagRepository.findById(tagId);
            String tagKeyword = tagKeywordtemp.get().getKeyword();
            tags.add(tagKeyword);
        }

        List<Long> likedUsers = new ArrayList<>();
        List<Likes> likesList = likesRepository.findAllByArticle_ArticleId(articleId);
        for (Likes eachLikes: likesList){
            Long likeUserId = eachLikes.getLikeUserId();
            likedUsers.add(likeUserId);
        }

        String createdDate = String.valueOf(article.getCreatedDate());

        ArticleResponseDto detailArticleResponseDto = new ArticleResponseDto(
                articleId,
                writerId,
                writerNickname,
                likedUsers,
                imageUrl,
                tags,
                createdDate);

        return detailArticleResponseDto;

    }

    // 내가 올린 게시물 조회
    @Transactional
    public List<ArticleResponseDto> getMyArticle(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<ArticleResponseDto> responseDtoList = new ArrayList<>();

        Long uid = userDetails.getUser().getUid();
        List<Article> articleList = articleRepository.findAllByUser_Uid(uid);

        //for문을 돌며 각 아티클을 articleResponseDto 형식으로 만들어주고
        for(Article eachArticle : articleList){
            Long articleId = eachArticle.getArticleId();
            User user = eachArticle.getUser();
            Long writerId = user.getUid();
            String writerNickname = user.getNickname();
            String imageUrl = eachArticle.getImageUrl();

            //내 아티클 아이디에 해당하는 아티클태그 객체 값들을 모두 불러왔음
            // [{articletag_id : 100, article_id : 2, tag_id : 3},{articletag_id : 101, article_id : 2, tag_id : 5}]
            List<ArticleTag> articleTags = articleTagRepository.findAllByArticle_ArticleId(articleId);

            // articleTags 포문을 돌면서 tag_id를 꺼내고, tagRepository에서 tag name을 꺼내서 tags 리스트에 하나씩 넣어준다
            List<String> tags = new ArrayList<>();

            for(ArticleTag eachArticleTags : articleTags){
                Long tagId = eachArticleTags.getTag().getTagId();
                //없으면 에러내야하는데 그냥 안함
                Optional<Tag> tagKeywordtemp = tagRepository.findById(tagId);
                String tagKeyword = tagKeywordtemp.get().getKeyword();
                tags.add(tagKeyword);
            }

            List<Long> likedUsers = new ArrayList<>();
            List<Likes> likesList = likesRepository.findAllByArticle_ArticleId(articleId);
            for (Likes eachLikes: likesList){
                Long likeUserId = eachLikes.getLikeUserId();
                likedUsers.add(likeUserId);
            }
            String createdDate = String.valueOf(eachArticle.getCreatedDate());

            ArticleResponseDto articleResponseDto = new ArticleResponseDto(articleId, writerId, writerNickname, likedUsers, imageUrl,tags, createdDate);

            //반환할 리스트에 하나씩 넣어준다
            responseDtoList.add(articleResponseDto);
        }
        //리스트를 돌려준다
        return responseDtoList;
    }

    // 내가 좋아한 사진 조회
    @Transactional
    public List<ArticleResponseDto> getMyLikeArticle(UserDetailsImpl userDetails) {
        List<ArticleResponseDto> responseDtoList = new ArrayList<>();

        Long uid = userDetails.getUser().getUid();
        List<Likes> LikearticleList = likesRepository.findAllByLikeUserId(uid);

        List<Article> articleList = new ArrayList<>();

        // [{article}, {article}, {article}]
        for(Likes eachLike : LikearticleList){
            articleList.add(eachLike.getArticle());
        }

        //for문을 돌며 각 아티클을 articleResponseDto 형식으로 만들어주고
        for(Article eachArticle : articleList){
            Long articleId = eachArticle.getArticleId();
            User user = eachArticle.getUser();
            Long writerId = user.getUid();
            String writerNickname = user.getNickname();
            String imageUrl = eachArticle.getImageUrl();

            //내 아티클 아이디에 해당하는 아티클태그 객체 값들을 모두 불러왔음
            // [{articletag_id : 100, article_id : 2, tag_id : 3},{articletag_id : 101, article_id : 2, tag_id : 5}]
            List<ArticleTag> articleTags = articleTagRepository.findAllByArticle_ArticleId(articleId);

            // articleTags 포문을 돌면서 tag_id를 꺼내고, tagRepository에서 tag name을 꺼내서 tags 리스트에 하나씩 넣어준다
            List<String> tags = new ArrayList<>();

            for(ArticleTag eachArticleTags : articleTags){
                Long tagId = eachArticleTags.getTag().getTagId();
                //없으면 에러내야하는데 그냥 안함
                Optional<Tag> tagKeywordtemp = tagRepository.findById(tagId);
                String tagKeyword = tagKeywordtemp.get().getKeyword();
                tags.add(tagKeyword);
            }

            List<Long> likedUsers = new ArrayList<>();
            List<Likes> likesList = likesRepository.findAllByArticle_ArticleId(articleId);
            for (Likes eachLikes: likesList){
                Long likeUserId = eachLikes.getLikeUserId();
                likedUsers.add(likeUserId);
            }
            String createdDate = String.valueOf(eachArticle.getCreatedDate());

            ArticleResponseDto articleResponseDto = new ArticleResponseDto(articleId, writerId, writerNickname, likedUsers, imageUrl,tags, createdDate);

            //반환할 리스트에 하나씩 넣어준다
            responseDtoList.add(articleResponseDto);
        }
        //리스트를 돌려준다
        return responseDtoList;
    }


//    public List<ArticleResponseDto> likeArticle(User user) {
//    }




}