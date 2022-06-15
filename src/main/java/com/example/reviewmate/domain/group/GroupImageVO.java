package com.example.reviewmate.domain.group;

import com.example.reviewmate.domain.user.UserVO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "GROUP_IMAGE_TB")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupImageVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx")
    private Long idx;

    @Column(name = "name")
    private String name;


    @CreatedDate
    @Column(name = "created_at")
    LocalDateTime createdAt;

    /*
    그룹은 이미지 한개 가질 수 있다. 이미지는 하나의 그룹 가질수 있다.
    하지만 이미지가 없을 경우 기본이미지로 설정해둘거기때문에, 그룹측에서는 ManyToOne
     */
    @OneToMany
    @JoinColumn(name = "group_idx_fk")
    private List<GroupVO> groups = new ArrayList<>();
}
