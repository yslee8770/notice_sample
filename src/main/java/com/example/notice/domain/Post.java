package com.example.notice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    private Long authorId;

    private Integer viewCount;

    private Integer likeCount;

    private Post(String title, String content, Long authorId, Integer viewCount, Integer likeCount) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
    }

    public static Post create(String title, String content, Long authorId) {
        return new Post(title, content, authorId, 0, 0);
    }


}
