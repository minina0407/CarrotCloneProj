package com.example.reviewmate.domain.comment.entity;

import com.example.reviewmate.domain.todo.entity.FeedBackVO;
import com.example.reviewmate.domain.user.entity.UserVO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "COMMENT_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx",updatable = false,nullable = false)
    private Long idx;

    @Column(name = "contents",nullable = false,updatable = true)
    private String contents;

    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    LocalDateTime createdAt;

    @CreatedDate
    @Column(name = "updated_at",nullable = true,updatable = true)
    LocalDateTime updatedAt;

    @CreatedDate
    @Column(name = "deleted_at",nullable = false,updatable = false)
    LocalDateTime deletedAt;

    /*
    user는 댓글을 여러개 쓸 수 있다. 댓글은 한 유저만 쓸 수 있다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx_fk", nullable = false, updatable = false)
    UserVO user;

    /*
    피드백 게시글은 여러개의 댓글을 가질 수 있다. 댓글은 한 게시글에 쓸 수 있다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedback_idx_fk",nullable = true)
    FeedBackVO feedback;
}
