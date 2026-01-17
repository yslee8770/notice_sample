package com.example.notice.service;

import com.example.notice.domain.Notice;
import com.example.notice.dto.LikeResponse;
import com.example.notice.repository.LikeRepository;
import com.example.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final LikeService likeService;
    private final LikeRepository likeRepository;
    private final NoticeRepository noticeRepository;

    @Transactional
    public LikeResponse like(long noticeId, long userId) {

        if (hasLikedByUser(noticeId, userId)) {
            Notice notice = noticeRepository.findById(noticeId)
                    .orElseThrow(() -> new RuntimeException("notice not found"));
            notice.minusLikeCount();
            return LikeResponse.from(false, notice.getLikeCount());
        } else {
            Notice notice = noticeRepository.findById(noticeId)
                    .orElseThrow(() -> new RuntimeException("notice not found"));
            notice.plusLikeCount();
            return LikeResponse.from(true, notice.getLikeCount());
        }
    }

    @Transactional(readOnly = true)
    public boolean hasLikedByUser(long userId, long noticeId) {
        boolean isPresent = likeRepository.findByUserIdAndNoticeId(userId, noticeId).isPresent();
        if(isPresent){
            likeService.delete(userId,noticeId);
        }else {
            likeService.insert(userId,noticeId);
        }
        return isPresent;
    }
}
