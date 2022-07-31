package com.example.reviewmate.domain.user.entity;


import com.example.reviewmate.domain.like.LikeVO;
import com.example.reviewmate.domain.todo.entity.FeedBackVO;
import com.example.reviewmate.domain.todo.entity.TodoVO;
import com.example.reviewmate.domain.comment.entity.CommentVO;
import com.example.reviewmate.domain.group.entity.GroupVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "USER_TB")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", updatable = false, nullable = false)
    private Long idx;


    @Column(name = "email", updatable = false, nullable = false, length = 100)
    private String email;

    @Column(name = "password", updatable = true, nullable = false, length = 255)
    String password;

    @Column(name = "phone", updatable = true, nullable = true, length = 255)
    String phone;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    LocalDateTime createdAt;

    @Column(name = "nickname", updatable = true, nullable = false, length = 255)
    String nickname;

    @OneToOne(targetEntity = AuthVO.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_name_fk", nullable = false, updatable = true)
    AuthVO auth;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TodoVO> todos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_image_idx_fk")
    private UserImageVO userImage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<CommentVO> comments = new ArrayList<>();

    /*
    유저는 여러 그룹에 가입할 수 있다. 하나의 그룹은 여러 유저를 가질 수 있다.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<GroupVO> groups = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<FeedBackVO> feedBacks = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<LikeVO> likes = new ArrayList<>();


    @Builder
    public UserVO(Long idx, String password, String email, String phone, LocalDateTime createdAt, String nickname, AuthVO auth) {
        this.auth = auth;
        this.email = email;
        this.password = password;
        this.idx = idx;
        this.phone = phone;
        this.createdAt = createdAt;
        this.nickname = nickname;
    }
}
