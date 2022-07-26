package com.example.reviewmate.domain.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageTodoDTO {
    List<TodoDTO> todos = new ArrayList<>();

    @JsonProperty("total_page")
    int totalPage;

    @JsonProperty("current_page")
    int current_page;

    @Builder
    public PageTodoDTO(List<TodoDTO> todos,int totalPage,int current_page){
        this.todos = todos;
        this.current_page= current_page;
        this.totalPage = totalPage;
    }
}
