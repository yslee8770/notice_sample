package com.example.notice.repository;

import com.example.notice.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    
    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);

    int deleteByPostIdAndUserId(Long postId, Long userId);
}
