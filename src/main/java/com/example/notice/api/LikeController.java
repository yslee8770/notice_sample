package com.example.notice.api;

import com.example.notice.dto.LikeResponse;
import com.example.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/targets/{noticeId}/likes/toggle")
@RequiredArgsConstructor
public class LikeController {

    private final NoticeService noticeService;

    @PostMapping("/{userId}")
    public ResponseEntity<LikeResponse> toggleLike(@PathVariable("targetId") long noticeId, @PathVariable("userId") long userId) {
        return ResponseEntity.ok(noticeService.like(noticeId,userId));
    }
}
