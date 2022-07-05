package com.example.reviewmate.domain.todo.controller;

import com.example.reviewmate.domain.message.MessageDTO;
import com.example.reviewmate.domain.todo.dto.CreateTodo;
import com.example.reviewmate.domain.todo.service.TodoFindService;
import com.example.reviewmate.domain.todo.service.TodoUpdateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
public class TodoController {

    TodoFindService todoFindService;
    TodoUpdateService todoUpdateService;

    public TodoController(TodoFindService todoFindService, TodoUpdateService todoUpdateService) {
        this.todoFindService = todoFindService;
        this.todoUpdateService = todoUpdateService;
    }

    @PostMapping(path = "/v1/todo")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<CreateTodo.Response> saveTodo(
            @RequestBody @Valid CreateTodo.Request request) {

        Long idx = todoUpdateService.save(request);

        return new ResponseEntity<>(new CreateTodo.Response(idx), HttpStatus.CREATED);

    }

    @PutMapping(path = "/v1/todo/{todo-idx}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<MessageDTO> updateTodo(
            @PathVariable(value = "todo-idx") Long todoIdx
    ) {
        todoUpdateService.updateContent(todoIdx);
        return new ResponseEntity<>(new MessageDTO("successs"), HttpStatus.OK);
    }
}
