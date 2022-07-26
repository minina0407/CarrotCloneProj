package com.example.reviewmate.domain.todo.service;

import com.example.reviewmate.domain.todo.entity.TodoVO;
import com.example.reviewmate.domain.todo.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.Optional;

@Service
public class TodoFindService {
    TodoRepository todoRepository;

    public TodoFindService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional(readOnly = true)
    public TodoVO findByIdx(Long idx) {
        Optional<TodoVO> todo = todoRepository.findById(idx);

        if (todo.isEmpty())
            throw new RuntimeException();

        return todo.get();

    }

    @Transactional(readOnly = true)
    public Page<TodoVO> findAll(Pageable pageable){
        return todoRepository.findAll(pageable);
    }

}
