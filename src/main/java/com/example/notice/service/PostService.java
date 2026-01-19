package com.example.notice.service;

import com.example.notice.domain.Like;
import com.example.notice.domain.Post;
import com.example.notice.domain.ViewLog;
import com.example.notice.dto.PostResponse;
import com.example.notice.repository.LikeRepository;
import com.example.notice.repository.PostRepository;
import com.example.notice.repository.ViewLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PostService {

    private final ViewLogRepository viewLogRepository;
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public PostResponse getPost(Long postId, Long userId) {
        boolean likeByMe = false;
        if (userId == null) {
            postRepository.updateViewCount(postId);
        } else {
            if (isViewLogPresent(postId, userId)) {
                updateViewCount(postId, userId);
                likeByMe = true;
            } else {
                try {
                    ViewLog viewLog = ViewLog.create(postId, userId);
                    viewLogRepository.save(viewLog);
                    postRepository.updateViewCount(postId);
                } catch (DataIntegrityViolationException e) {
                    return PostResponse.from(selectPostByPostId(postId), true);
                }
            }
            if (isLikedByMe(postId, userId)) {
                likeByMe = true;
            }
        }
        return PostResponse.from(selectPostByPostId(postId), likeByMe);
    }

    @Transactional(readOnly = true)
    public boolean isViewLogPresent(Long postId, Long userId) {
        return viewLogRepository.findByPostIdAndUserId(postId, userId).isPresent();
    }

    @Transactional
    public PostResponse like(Long postId, Long userId) {
        if (isLikedByMe(postId, userId)) {
            return PostResponse.from(selectPostByPostId(postId), true);
        }
        try {
            Like like = Like.create(postId, userId);
            likeRepository.save(like);
            postRepository.updatePlusLikeCount(postId);
        } catch (DataIntegrityViolationException e) {
            return PostResponse.from(selectPostByPostId(postId), true);
        }

        return PostResponse.from(selectPostByPostId(postId), true);
    }

    @Transactional
    public PostResponse unLike(Long postId, Long userId) {
        if (isLikedByMe(postId, userId)) {
            int deleteCount = likeRepository.deleteByPostIdAndUserId(postId, userId);
            if (deleteCount > 0) {
                postRepository.updateMinusLikeCount(postId);
            }
        }
        return PostResponse.from(selectPostByPostId(postId), false);
    }

    @Transactional(readOnly = true)
    public boolean isLikedByMe(Long postId, Long userId) {
        return likeRepository.findByPostIdAndUserId(postId, userId).isPresent();
    }

    @Transactional
    protected void updateViewCount(Long postId, Long userId) {
        int updateCount = viewLogRepository.updateLastViewTimeByPostIdAndUserId(
                postId,
                userId,
                Instant.now().minusSeconds(10)
        );

        if (updateCount > 0) {
            postRepository.updateViewCount(postId);
        }
    }

    @Transactional(readOnly = true)
    protected Post selectPostByPostId(long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }
}
