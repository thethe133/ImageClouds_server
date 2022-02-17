package com.sparta.week06login.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor

public class Article extends Timestampped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long articleId;

    @ManyToOne
    @JoinColumn(name = "WRITER_ID", nullable = false)
    private User user;

    @Column(nullable = false)
    private String imageUrl;

    public Article(User user, String imageUrl) {
        this.user = user;
        this.imageUrl = imageUrl;
    }
}

