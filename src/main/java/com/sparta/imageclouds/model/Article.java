package com.sparta.imageclouds.model;

import com.sparta.imageclouds.dto.ArticleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Article extends Timestampped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long articleId;                        //articleId

    @Column(nullable = false)
    private Long writerId;             // uid

    @Column(nullable = false)
    private String imageUrl;            //imageUrl

    //createdDate     // DB설계에 String이라고 되어있는데? 고쳐야하나?



//    @LastModifiedDate
//    private LocalDateTime modifiedDate;

//    @ManyToOne
//    @JoinColumn(name = "writer_id", nullable = false)           // user table 의 uid
//    private User uid;


    public Article(ArticleRequestDto articleRequestDto) {
//        this.articleId = articleRequestDto.getArticleId();
        this.writerId = articleRequestDto.getWriterId();
        this.imageUrl = articleRequestDto.getImageUrl();




    }


}
