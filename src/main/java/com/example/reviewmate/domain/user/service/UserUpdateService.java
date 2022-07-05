package com.example.reviewmate.domain.user.service;

import com.example.reviewmate.domain.user.dto.CreateUser;
import com.example.reviewmate.domain.user.entity.AuthVO;
import com.example.reviewmate.domain.user.entity.UserVO;
import com.example.reviewmate.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserUpdateService {
    private final PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    AuthFindService authFindService;


    public UserUpdateService(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthFindService authFindService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authFindService = authFindService;
    }
    @Transactional
    public Long signUp(CreateUser.Request request){
        if(userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("이미 가입되어있는 유저입니다.");

        AuthVO auth = authFindService.getByName(AuthVO.Type.USER.value()); //기본은 user타입으로 셋팅

        UserVO vo = UserVO.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .phone(request.getPhone())
                .auth(auth)
                .build();

        return userRepository.save(vo).getIdx();
    }


}
