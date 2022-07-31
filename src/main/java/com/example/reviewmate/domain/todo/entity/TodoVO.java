package com.example.reviewmate.domain.todo.entity;

import com.example.reviewmate.domain.like.LikeVO;
import com.example.reviewmate.domain.user.entity.UserVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "TODO_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", updatable = false, nullable = false)
    private Long idx;

    @Column(name = "contents", updatable = true, nullable = false)
    private String contents;

    @Column(name = "state", updatable = true, nullable = false)
    private int state;

    /*
    유저는 여러개의 할일을 가질 수 있다. 할일은 하나의 유저만 가질 수 있다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx_fk", nullable = false, updatable = false)
    UserVO user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "todo", cascade = CascadeType.ALL)
    List<LikeVO> likes = new ArrayList<>();

    public enum STATE {
        UNCOMPLETED,
        COMPLETED
    }

    @Builder
    public TodoVO(String contents, UserVO user, int state) {
        this.contents = contents;
        this.user = user;
        this.state = state;
    }

    public void updateContent(String contents) {
        this.contents = contents;

    }

    public void changeState(int state) {
        this.state = state;
    }

}
