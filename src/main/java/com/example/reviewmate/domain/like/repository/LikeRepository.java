package com.example.reviewmate.domain.like.repository;

import com.example.reviewmate.domain.like.LikeVO;
import com.example.reviewmate.domain.todo.entity.TodoVO;
import com.example.reviewmate.domain.user.entity.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikeVO, Long> {
    // 해당 todo에 해당 회원이 응원을 남긴적이 있는지 체크위해
    Optional<LikeVO> findByUserAndTodo(UserVO user, TodoVO todo);

    LikeVO findLikeVOByUserAndTodo(UserVO user, TodoVO todo);
}
