package com.example.reviewmate.domain.config;

import com.example.reviewmate.jwt.JwtAccessDeniedHandler;
import com.example.reviewmate.jwt.JwtAuthenticationEntryPoint;
import com.example.reviewmate.jwt.JwtSecurityConfig;
import com.example.reviewmate.jwt.TokenProvider;
import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //WebSecurityConfigurerAdapter이거 상속하는 경우 달아줌
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 이렇게 설정할 경우, 사이트 전체가 잠겨서 -> 비번을 쳐야 접근 가능
    //권한 설정
    //페이지의 인증 해제작업
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final TokenProvider tokenProvider;
    public SecurityConfig(
            TokenProvider tokenProvider,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        //비밀번호를 암호화하기 위한 다양한 알고리즘을 사용가능
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        //경로별 권한 설정
        httpSecurity
                .csrf().disable() // csrf 관련 설정 비활성
                .formLogin().disable()//formlogin 비활성화
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 인증 예외 처리 
                .accessDeniedHandler(jwtAccessDeniedHandler) // 인가 예외 처리

                // 세션을 유지하지 않도록 설정
                .and()
                .authorizeRequests().anyRequest().permitAll()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));







    }


}
