package com.example.reviewmate.domain.todo.dto;


import com.example.reviewmate.domain.todo.entity.TodoVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TodoDTO {

    private String contents;
    private int state;
    private Long idx;

    @Builder
    public TodoDTO(Long idx, String contents, int state) {
        this.contents = contents;
        this.state = state;
        this.idx = idx;
    }

    public static TodoDTO fromEntity(TodoVO todo){
        return TodoDTO.builder()
                .contents(todo.getContents())
                .idx(todo.getIdx())
                .state(todo.getState())
                .build();
    }
}
