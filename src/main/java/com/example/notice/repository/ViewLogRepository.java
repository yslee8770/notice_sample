package com.example.notice.repository;

import com.example.notice.domain.ViewLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Optional;


public interface ViewLogRepository extends JpaRepository<ViewLog, Long> {

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("""
                    update ViewLog v set v.lastViewTime = CURRENT_TIMESTAMP
                    where
                    v.postId=:postId
                    and v.userId=:userId
                    and v.lastViewTime < :cutoff
            """)
    int updateLastViewTimeByPostIdAndUserId(Long postId, Long userId, Instant cutoff);

    Optional<ViewLog> findByPostIdAndUserId(Long postId, Long userId);
}