package com.example.reviewmate.domain.user.service;

import com.example.reviewmate.domain.user.entity.AuthVO;
import com.example.reviewmate.domain.user.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthFindService {

    @Autowired
    AuthRepository authRepository;

    @Transactional(readOnly = true)
    public AuthVO getByName(String name) {
        Optional<AuthVO> auth = authRepository.findById(name);

        if (auth.isEmpty())
            throw new RuntimeException();

        return auth.get();
    }
}
