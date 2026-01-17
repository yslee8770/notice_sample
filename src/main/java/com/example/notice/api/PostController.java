package com.example.notice.api;

import com.example.notice.dto.PostResponse;
import com.example.notice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long postId,
                                                @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        return ResponseEntity.ok(postService.getPost(postId, userId));
    }

    @PutMapping("{postId}/likes")
    public ResponseEntity<PostResponse> like(@PathVariable Long postId,
                                     @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        return ResponseEntity.ok(postService.like(postId, userId));
    }

    @DeleteMapping("{postId}/likes")
    public ResponseEntity<PostResponse> unLike(@PathVariable Long postId,
                                       @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        return ResponseEntity.ok(postService.unLike(postId, userId));
    }
}
