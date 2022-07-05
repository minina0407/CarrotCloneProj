package com.example.reviewmate.domain.todo.repository;

import com.example.reviewmate.domain.todo.entity.FeedBackVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBackVO, Long> {
}
