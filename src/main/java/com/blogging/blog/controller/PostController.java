package com.blogging.blog.controller;

import com.blogging.blog.payloads.PostDto;
import com.blogging.blog.payloads.ResponseHandler;
import com.blogging.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/start")
    ResponseEntity<Object> start(){
        return ResponseHandler.responseBuilder("Post Service is Started", HttpStatus.OK,this.postService.start());
    }

    @PostMapping("/{postId}")
    ResponseEntity<Object> createPost(PostDto postDto,Integer userId, Integer categoryId){
        return ResponseHandler.responseBuilder("",HttpStatus.OK,this.postService.createPost(postDto,userId,categoryId));
    }


}
