package com.example.reviewmate.domain.user.repository;

import com.example.reviewmate.domain.user.entity.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserVO, Long> {
    public Optional<UserVO> findOneByEmail(String email);

    public boolean existsByEmail(String email);

}
