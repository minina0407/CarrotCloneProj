package com.example.reviewmate.domain.like.controller;

import com.example.reviewmate.domain.like.service.LikeUpdateService;
import com.example.reviewmate.domain.message.MessageDTO;
import com.example.reviewmate.domain.todo.service.TodoFindService;
import com.example.reviewmate.domain.user.service.UserFindService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/api")
public class LikeController {
    LikeUpdateService likeUpdateService;
    TodoFindService todoFindService;
    UserFindService userFindService;

    public LikeController(LikeUpdateService likeUpdateService, TodoFindService todoFindService, UserFindService userFindService) {
        this.likeUpdateService = likeUpdateService;
        this.todoFindService = todoFindService;
        this.userFindService = userFindService;
    }

    @PostMapping(path = "/v1/todo/{todo-idx}/likes")
    @PreAuthorize("hasRole('ROLE_USER')")

    public ResponseEntity<MessageDTO> addLike(

            @PathVariable(value = "todo-idx") Long todoIdx
    ) {

        likeUpdateService.addLike(todoIdx);
        return new ResponseEntity<>(new MessageDTO("add like success"), HttpStatus.OK);

    }

    @DeleteMapping(path = "/v1/todo/{todo-idx}/likes")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<MessageDTO> cancelLike(
            @PathVariable(value = "todo-idx") Long todoIdx
    ) {
        likeUpdateService.cancelLike(todoIdx);
        return new ResponseEntity<>(new MessageDTO("cancel like"), HttpStatus.NO_CONTENT);
    }

}
