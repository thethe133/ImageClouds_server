package com.sparta.week06login.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor

public class ArticleTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long articletagId;

    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID", nullable = false)
    private Article article;

    @ManyToOne
    @JoinColumn(name = "TAG_ID", nullable = false)
    private Tag tag;

    public ArticleTag(Article article, Tag tag) {
        this.article = article;
        this.tag = tag;
    }
}
