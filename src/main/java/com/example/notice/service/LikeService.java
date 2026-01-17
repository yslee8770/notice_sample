package com.example.notice.service;

import com.example.notice.domain.Like;
import com.example.notice.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    @Transactional
    @Async
    public void delete(long noticeId, long userId) {
        likeRepository.deleteByUserIdAndNoticeId(noticeId,userId);
    }

    @Transactional
    @Async
    public void insert(Long userId, Long noticeId) {
        Like like = Like.create(userId,noticeId);
    }
}
