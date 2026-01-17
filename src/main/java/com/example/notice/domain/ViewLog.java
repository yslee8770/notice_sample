package com.example.notice.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ViewLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "view_log_id")
    private Long id;

    private Long postId;

    private Long userId;


    @CreatedDate
    private Instant lastViewTime;

    private ViewLog(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public static ViewLog create(Long postId, Long userId) {
        return new ViewLog(postId, userId);
    }

}
