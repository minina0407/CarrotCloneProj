package com.example.reviewmate.domain.group;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

}
