package com.blogging.blog.controller;

import com.blogging.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blog")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/start")
    public ResponseEntity<?> startUserService(){
        String message= userService.startUserService();
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
