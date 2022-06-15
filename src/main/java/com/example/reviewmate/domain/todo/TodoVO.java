package com.example.reviewmate.domain.todo;

import com.example.reviewmate.domain.user.UserVO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TODO_TB")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx",updatable = false,nullable = false)
    private Long idx;

    @Column(name = "contents",updatable = true,nullable = false)
    private String contents;

    @Column(name = "state",updatable = true,nullable = false)
    private String state; // TODO// enum으로 고쳐야하나 고민해보기

    /*
    유저는 여러개의 할일을 가질 수 있다. 할일은 하나의 유저만 가질 수 있다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx_fk",nullable = false,updatable = false)
    UserVO user;


 }
