package com.example.reviewmate.domain.todo;



import com.example.reviewmate.domain.comment.CommentVO;
import com.example.reviewmate.domain.user.UserVO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FEEDBACK_TB")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedBackVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "title",nullable = false,updatable = true)
    private String title;

    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @CreatedDate
    @Column(name = "updated_at",nullable = true,updatable = true)
    private LocalDateTime updatedAt;

    /*
    user는 피드백게시글을 여러개 작성할 수 있다. 게시글은 한 사람에의해 작성될 수 있다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx_fk")
    UserVO user;

    /*
    피드백 게시글은 여러개의 댓글을 가질 수 있다.
     */
    @OneToMany(fetch = FetchType.LAZY)
    List <CommentVO> comments = new ArrayList<>();


}
