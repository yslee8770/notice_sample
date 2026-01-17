package com.example.notice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    private String title;

    private int likeCount;

    @Version
    private int version;

    private Notice(String title, int likeCount) {
        this.likeCount = likeCount;
    }

    public static Notice create(String title) {
        return new Notice(title, 0);
    }

    public void plusLikeCount() {
        likeCount++;
    }
    public void minusLikeCount() {
        likeCount--;
    }

}
