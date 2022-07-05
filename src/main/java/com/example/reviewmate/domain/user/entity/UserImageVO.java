package com.example.reviewmate.domain.user.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "USER_IMAGE_TB")
public class UserImageVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "name",updatable = false,nullable = false)
    private String name;


    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    LocalDateTime createdAt;

    /*
    유저는 이미지 한개 가질 수 있다. 이미지는 한 명의 유저 가질수 있다.
    하지만 이미지가 없을 경우 기본이미지로 설정해둘거기때문에, 유저측에서는 ManyToOne
     */
    @OneToMany(mappedBy = "userImage")
    private List<UserVO> users = new ArrayList<>();


}
