package com.sparta.week06login.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID", nullable = false)
    private Article article;

    @Column(nullable = false)
    private Long likeUserId;

    public Likes(Article article, Long uid){
        this.article = article;
        this.likeUserId = uid;

    }

}