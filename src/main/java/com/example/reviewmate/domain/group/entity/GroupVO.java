package com.example.reviewmate.domain.group.entity;


import com.example.reviewmate.domain.group.entity.GroupImageVO;
import com.example.reviewmate.domain.todo.entity.FeedBackVO;
import com.example.reviewmate.domain.user.entity.UserVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name = "GROUP_TB")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx",updatable = false,nullable = false)
    private Long idx;

    @Column(name = "name",updatable = true,nullable = false,length = 200)
    private String name;

    @Column(name = "description",updatable = true,nullable = false,length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_image_fk")
    private GroupImageVO groupImage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_idx_fk")
    private UserVO user;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedback_idx_fk")
    private List<FeedBackVO> feedBacks;

    @Builder
    public GroupVO(String name,UserVO user){
        this.name = name;
        this.user = user;
    }
}
