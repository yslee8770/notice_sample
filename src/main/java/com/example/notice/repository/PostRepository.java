package com.example.notice.repository;

import com.example.notice.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("""
            update Post p 
            set p.viewCount=p.viewCount+1
            where p.id=:postId 
            """)
    void updateViewCount(@Param("postId") Long postId);

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("""
            update Post p 
            set p.likeCount=p.likeCount+1
            where p.id=:postId 
            """)
    void updatePlusLikeCount(@Param("postId") Long postId);

    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query("""
            update Post p 
            set p.likeCount=p.likeCount-1
            where p.id=:postId 
            """)
    void updateMinusLikeCount(@Param("postId") Long postId);
}
