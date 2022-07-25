package com.example.reviewmate.domain.group.service;

import com.example.reviewmate.domain.group.dto.CreateGroup;
import com.example.reviewmate.domain.group.entity.GroupVO;
import com.example.reviewmate.domain.group.repository.GroupRepository;
import com.example.reviewmate.domain.user.entity.UserVO;
import com.example.reviewmate.domain.user.service.UserFindService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupUpdateService {
    GroupRepository groupRepository;
    UserFindService userFindService;

    public GroupUpdateService(GroupRepository groupRepository, UserFindService userFindService) {
        this.groupRepository = groupRepository;
        this.userFindService = userFindService;
    }

    // 그룹 생성
    @Transactional
    public Long save(CreateGroup.Request request){
        UserVO user = userFindService.getMyUserWithAuthorities();

        GroupVO group = GroupVO.builder()
                .user(user)
                .name(request.getName())
                .build();
        return groupRepository.save(group).getIdx();
    }



}