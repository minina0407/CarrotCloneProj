package com.example.reviewmate.domain.user.service;

import com.example.reviewmate.domain.user.repository.UserRepository;
import com.example.reviewmate.jwt.RefreshTokenRepository;
import com.example.reviewmate.jwt.TokenProvider;
import com.example.reviewmate.jwt.dto.TokenDTO;
import com.example.reviewmate.jwt.dto.TokenRequestDTO;
import com.example.reviewmate.jwt.entity.RefreshTokenVO;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthUpdateService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthUpdateService(AuthenticationManagerBuilder authenticationManagerBuilder, UserRepository userRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, RefreshTokenRepository refreshTokenRepository) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Transactional
    public TokenDTO reissue(TokenRequestDTO request) {
        if (!tokenProvider.validateToken(request.getRefreshToken())) {
            throw new RuntimeException("refresh token이 유효하지 않습니다.");

        }

        Authentication authentication = tokenProvider.getAuthentication(request.getAccessToken());

        RefreshTokenVO refreshTokenVO = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃된 사용자입니다."));

        if (!refreshTokenVO.getValue().equals(request.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치 하지 않습니다.");
        }

        TokenDTO tokenDto = tokenProvider.createToken(authentication);

        RefreshTokenVO refreshToken = refreshTokenVO.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(refreshToken);

        return tokenDto;

    }


}
