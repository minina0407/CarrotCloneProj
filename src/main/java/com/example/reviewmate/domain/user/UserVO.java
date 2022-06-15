package com.example.reviewmate.domain.user;


import com.example.reviewmate.domain.todo.TodoVO;
import com.example.reviewmate.domain.comment.CommentVO;
import com.example.reviewmate.domain.group.GroupVO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

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
    @Column(name = "idx",updatable = false,nullable = false)
    private Long idx;


    @Column(name = "id",updatable = true,nullable = false)
    private String id;

    @Column(name = "email",updatable = false,nullable = false,length = 100)
    private String email;

    @Column(name = "password",updatable = true, nullable = false,length = 255)
    String password;

    @Column(name = "phone",updatable = true,nullable = true,length = 255)
    String phone;

    @CreatedDate
    @Column(name = "created_at",updatable = false,nullable = false)
    LocalDateTime createdAt;

    @Column(name = "nickname",updatable = true,nullable = false,length = 255)
    String nickname;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_name_fk",nullable = false,updatable = true)
    AuthVO auth;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<TodoVO> todos=new ArrayList<>();

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "user_image_idx_fk")
   private UserImageVO userImage;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_idx_fk",updatable = false,nullable = false)
   CommentVO comment;

    /*
    유저는 여러 그룹에 가입할 수 있다. 하나의 그룹은 여러 유저를 가질 수 있다.
     */

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_idx_fk")
    private List<GroupVO> groups = new ArrayList<>();


}
