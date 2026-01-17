package com.example.notice.repository;

import com.example.notice.domain.Like;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Like> findByUserIdAndNoticeId(Long userId, Long noticeId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
            delete from Like l
            where
             l.noticeId=:noticeId
            and l.userId=:userId 
            """)
    void deleteByUserIdAndNoticeId(Long noticeId, Long userId);
}
