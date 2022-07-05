package com.example.reviewmate.domain.user.controller;

import com.example.reviewmate.domain.user.dto.AuthorizeUser;
import com.example.reviewmate.jwt.TokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/v1/user/authentication") // login과정
    public ResponseEntity<AuthorizeUser.Response> authorize(
            @RequestBody @Validated AuthorizeUser.Request request) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        // 로그인 id / pw를 기반으로  AuthenticationToken를 생성한다.
        // AuthenticationToken은 principal과 credentials(인증정보)를 인자로 받는 생성자를 통한 객체 생성
        // 기본값이 false로 설정되어있기 때문에, 아직 인증되지 않음, -> 인증을 위해 만들어진 객체가 된다.

        //인증성공후 Authentication 인증객체를 만들어서 내부의 Principal, Credentials을 채워넣는다.
        // 이후 SecurityContextHolder 객체안의 SecurityContext에 저장 -> 인증객체를 전역적으로 사용가능능
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        return new ResponseEntity<>(new AuthorizeUser.Response(jwt), HttpStatus.OK);
    }
}
