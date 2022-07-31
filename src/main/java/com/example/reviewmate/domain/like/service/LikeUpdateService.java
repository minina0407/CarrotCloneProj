package com.example.reviewmate.domain.like.service;

import com.example.reviewmate.domain.like.LikeVO;
import com.example.reviewmate.domain.like.repository.LikeRepository;
import com.example.reviewmate.domain.todo.entity.TodoVO;
import com.example.reviewmate.domain.todo.service.TodoFindService;
import com.example.reviewmate.domain.user.entity.UserVO;
import com.example.reviewmate.domain.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LikeUpdateService {
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    UserFindService userFindService;
    @Autowired
    TodoFindService todoFindService;

    public LikeUpdateService(LikeRepository likeRepository, UserFindService userFindService, TodoFindService todoFindService) {
        this.likeRepository = likeRepository;
        this.userFindService = userFindService;
        this.todoFindService = todoFindService;
    }


    @Transactional
    public void addLike(Long idx) {
        UserVO user = userFindService.getMyUserWithAuthorities();
        TodoVO todo = todoFindService.findByIdx(idx);

        // 자기 자신의 할일에 대해서는 응원할 수 없다.
        if (user == todo.getUser())
            throw new RuntimeException(); //todo : 예외처리 수정하기!!

        //한사람이 중복으로 한 todolist를 여러번 응원할 수는 없다.
        if (isNotAlreadyLiked(user, todo)) {
            LikeVO like = LikeVO.builder()
                    .user(user)
                    .todo(todo)
                    .build();
            likeRepository.save(like);
        }
    }

    public boolean isNotAlreadyLiked(UserVO user, TodoVO todo) {
        return likeRepository.findByUserAndTodo(user, todo).isEmpty();
    }

    @Transactional
    public void cancelLike(Long idx) {
        // 좋아요 취소는 자기가 응원한 좋아요의 todo만 취소할 수 있다.
        TodoVO todo = todoFindService.findByIdx(idx);
        UserVO user = userFindService.getMyUserWithAuthorities();
        LikeVO like = likeRepository.findLikeVOByUserAndTodo(user, todo);

        if (like.getUser() != user) {
            throw new RuntimeException();
        }
        likeRepository.delete(like);

    }
}

