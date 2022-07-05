package com.example.reviewmate.domain.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class CreateTodo {
    @Builder
    @Getter
    public static class Request {
        @NotBlank
        String content;
        @NotBlank
        String state;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Response {
        Long idx;
    }
}
