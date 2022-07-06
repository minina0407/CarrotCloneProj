package com.example.reviewmate.jwt;

import com.example.reviewmate.jwt.entity.RefreshTokenVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenVO,Long> {
    Optional<RefreshTokenVO> findByKey(String key);
}
