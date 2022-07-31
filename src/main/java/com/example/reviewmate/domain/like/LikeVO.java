package com.example.reviewmate.domain.like;

import com.example.reviewmate.domain.todo.entity.TodoVO;
import com.example.reviewmate.domain.user.entity.UserVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "LIKE_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false, updatable = false)
    private Long idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx_fk", nullable = false, updatable = false)
    private UserVO user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_idx_fk", nullable = true, updatable = true)
    private TodoVO todo;

    @Builder
    public LikeVO(UserVO user, TodoVO todo) {
        this.user = user;
        this.todo = todo;
    }
}
