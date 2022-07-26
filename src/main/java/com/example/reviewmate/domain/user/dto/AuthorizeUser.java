package com.example.reviewmate.domain.user.dto;

import com.example.reviewmate.jwt.dto.TokenDTO;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorizeUser {

    @Getter
    @Builder
    public static class Request{

        @NotBlank(message = "이메일이 입력되지 않았습니다.")
        private String email;

        @NotBlank(message = "비밀번호가 입력되지 않았습니다.")
        private String password;
    }

    @AllArgsConstructor
    @Getter
    public static class Response{
       TokenDTO tokenDTO;
    }
}
