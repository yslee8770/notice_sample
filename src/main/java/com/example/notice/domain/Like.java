package com.example.notice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    private Long postId;

    private Long userId;

    private Like(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public static Like create(Long postId, Long userId) {
        return new Like(postId, userId);
    }
}
