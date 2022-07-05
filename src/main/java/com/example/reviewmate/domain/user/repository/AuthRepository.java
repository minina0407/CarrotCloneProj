package com.example.reviewmate.domain.user.repository;

import com.example.reviewmate.domain.user.entity.AuthVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<AuthVO, String > {
}
