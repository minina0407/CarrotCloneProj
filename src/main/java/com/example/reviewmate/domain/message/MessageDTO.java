package com.example.reviewmate.domain.message;

import lombok.Getter;

@Getter
public class MessageDTO {
    String message;

    public MessageDTO(String message){
        this.message = message;
    }
}
