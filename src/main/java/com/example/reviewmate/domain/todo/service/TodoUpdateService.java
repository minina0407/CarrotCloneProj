package com.example.reviewmate.domain.todo.service;

import com.example.reviewmate.domain.todo.dto.CreateTodo;
import com.example.reviewmate.domain.todo.entity.TodoVO;
import com.example.reviewmate.domain.todo.repository.TodoRepository;
import com.example.reviewmate.domain.user.entity.UserVO;
import com.example.reviewmate.domain.user.service.UserFindService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TodoUpdateService {
    TodoRepository todoRepository;
    UserFindService userFindService;
    TodoFindService todoFindService;

    public TodoUpdateService(TodoRepository todoRepository, UserFindService userFindService, TodoFindService todoFindService) {
        this.todoRepository = todoRepository;
        this.userFindService = userFindService;
        this.todoFindService = todoFindService;
    }


    // 할 일 저장
    @Transactional
    public Long save(CreateTodo.Request request) {
        UserVO user = userFindService.getMyUserWithAuthorities();

        TodoVO todo = TodoVO.builder()
                .user(user)
                .contents(request.getContent())
                .state(TodoVO.STATE.UNCOMPLETED.ordinal()) // 할일을 처음에 작성한 경우에는 미완료 상태로
                .build();

        return todoRepository.save(todo).getIdx();
    }

    //할일 삭제
    @Transactional
    public void delete(Long idx) {
        TodoVO todo = todoFindService.findByIdx(idx);
        todoRepository.delete(todo);
    }


    @Transactional
    public void updateContent(Long idx,String content) {
        UserVO user = userFindService.getMyUserWithAuthorities();

        TodoVO todo = todoFindService.findByIdx(idx);

        todo.updateContent(content);

        todoRepository.save(todo);


    }

    // 할 일 수정 -> 상태만 변환
    @Transactional
    public void changeState(Long idx,int state) {
        TodoVO todo = todoFindService.findByIdx(idx);
        todo.changeState(state);
    }


}
