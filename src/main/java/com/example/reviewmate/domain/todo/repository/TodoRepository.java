package com.example.reviewmate.domain.todo.repository;

import com.example.reviewmate.domain.todo.entity.TodoVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoVO, Long> {
}
