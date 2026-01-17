package com.example.notice.dto;

public record LikeResponse(
        boolean liked,
        int likeCount
) {
    public static LikeResponse from(boolean liked, int likeCount) {
        return new LikeResponse(liked, likeCount);
    }

}
