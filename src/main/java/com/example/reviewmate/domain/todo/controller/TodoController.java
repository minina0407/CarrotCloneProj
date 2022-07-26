package com.example.reviewmate.domain.todo.controller;

import com.example.reviewmate.domain.message.MessageDTO;
import com.example.reviewmate.domain.todo.dto.CreateTodo;
import com.example.reviewmate.domain.todo.dto.PageTodoDTO;
import com.example.reviewmate.domain.todo.dto.TodoDTO;
import com.example.reviewmate.domain.todo.entity.TodoVO;
import com.example.reviewmate.domain.todo.service.TodoFindService;
import com.example.reviewmate.domain.todo.service.TodoUpdateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
            @PathVariable(value = "todo-idx") Long todoIdx,
            @RequestBody @Valid TodoDTO todoDTO
            ) {
        todoUpdateService.updateContent(todoIdx,todoDTO.getContents());
        return new ResponseEntity<>(new MessageDTO("success"), HttpStatus.OK);
    }

    @PutMapping(path = "/v1/todo/{todo-idx}/state")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<MessageDTO> updateState(
            @PathVariable(value = "todo-idx") Long todoIdx
    ){
        todoUpdateService.changeState(todoIdx, TodoVO.STATE.COMPLETED.ordinal());
        return new ResponseEntity<>(new MessageDTO("success"),HttpStatus.OK);
    }

    @DeleteMapping(path = "/v1/todo/{todo-idx}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<MessageDTO> deleteTodo(
            @PathVariable(value = "todo-idx") Long todoIdx
    ){
        todoUpdateService.delete(todoIdx);
        return new ResponseEntity<>(new MessageDTO("success"),HttpStatus.OK);
    }


    @GetMapping(path = "/v1/todo/{todo-idx}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<CreateTodo.Response> getTodo(
            @PathVariable(value = "todo-idx")Long todoIdx
    ){
        TodoVO todo =  todoFindService.findByIdx(todoIdx);
        return new ResponseEntity<>(new CreateTodo.Response(todo.getIdx()),HttpStatus.OK);
    }


}
