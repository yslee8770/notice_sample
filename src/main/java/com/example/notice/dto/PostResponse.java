package com.example.notice.dto;

import com.example.notice.domain.Post;

public record PostResponse (
        long postId,
        String title,
        String content,
        long authorId,
        long viewCount,
        long likeCount,
        boolean likeByMe
){
    public static PostResponse from (Post post,boolean likeByMe){
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthorId(),
                post.getViewCount(),
                post.getLikeCount(),
                likeByMe
        );
    }
}
