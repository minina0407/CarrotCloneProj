package com.example.reviewmate.jwt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenRequestDTO {

        private String accessToken;
        private String refreshToken;

}
