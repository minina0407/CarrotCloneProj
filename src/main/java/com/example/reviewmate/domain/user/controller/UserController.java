package com.example.reviewmate.domain.user.controller;

import com.example.reviewmate.domain.user.dto.CreateUser;
import com.example.reviewmate.domain.user.service.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserUpdateService userUpdateService;


    @PostMapping(path = "/v1/user")
    public ResponseEntity<CreateUser.Response>getSignup
            (@RequestBody @Validated CreateUser.Request request){
        Long idx= userUpdateService.signUp(request);

        return new ResponseEntity<>(new CreateUser.Response(idx), HttpStatus.OK);
    }


}
