package com.example.reviewmate.domain.like.dto;

import com.example.reviewmate.domain.todo.entity.TodoVO;
import com.example.reviewmate.domain.user.entity.UserVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class CreateLike {

    @Builder
    @Getter
    public static class Request {
        @NotBlank UserVO user;
        @NotBlank TodoVO todo;

    }

}
