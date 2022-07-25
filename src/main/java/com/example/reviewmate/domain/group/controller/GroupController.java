package com.example.reviewmate.domain.group.controller;

import com.example.reviewmate.domain.group.dto.CreateGroup;
import com.example.reviewmate.domain.group.service.GroupUpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.Column;
import javax.validation.Valid;

@Controller
public class GroupController {

    GroupUpdateService groupUpdateService;

    public GroupController(GroupUpdateService groupUpdateService) {
        this.groupUpdateService = groupUpdateService;
    }

    // 그룹 생성
    @PostMapping(path = "/v1/group")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<CreateGroup.Response> saveGroup(
            @RequestBody @Valid CreateGroup.Request request){
       Long idx=groupUpdateService.save(request);
       return new ResponseEntity(new CreateGroup.Response(idx),HttpStatus.CREATED);
    }



}
