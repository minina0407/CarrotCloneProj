package com.example.reviewmate.domain.user.service;

import com.example.reviewmate.domain.user.entity.UserVO;
import com.example.reviewmate.domain.user.repository.UserRepository;
import com.example.reviewmate.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserFindService {

    UserRepository userRepository;

    public UserFindService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public UserVO getMyUserWithAuthorities(){
        Optional<UserVO> userVO = SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneByEmail);

        if(userVO.isEmpty())
            throw new RuntimeException();

        return userVO.get();
    }
}
