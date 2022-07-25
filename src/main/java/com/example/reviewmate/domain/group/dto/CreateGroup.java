package com.example.reviewmate.domain.group.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class CreateGroup {
    @Builder
    @Getter
    public static class Request {
        @NotBlank
        String name;


    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Response {
        Long idx;
    }
}
