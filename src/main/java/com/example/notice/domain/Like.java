package com.example.notice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    private Long userId;

    private Long noticeId;

    private Like(Long userId, Long noticeId) {
        this.userId = userId;
        this.noticeId = noticeId;
    }

    public static Like create(Long userId, Long noticeId) {
        return new Like(userId, noticeId);
    }

}
