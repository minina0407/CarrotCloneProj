package com.example.reviewmate.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
// 유저 정보가 없이 접근할려고 하면 401에러를 내려준다.
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override // 인증 예외 발생시 수행 메서드
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}