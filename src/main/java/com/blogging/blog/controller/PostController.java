package com.blogging.blog.controller;

import com.blogging.blog.payloads.PostDto;
import com.blogging.blog.payloads.PostResponse;
import com.blogging.blog.payloads.ResponseHandler;
import com.blogging.blog.payloads.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/start")
    ResponseEntity<Object> start(){
        return ResponseHandler.responseBuilder("Post Service is Started", HttpStatus.OK,this.postService.start());
    }

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto, Integer userId, Integer categoryId){
        return ResponseHandler.responseBuilder("",HttpStatus.OK,this.postService.createPost(postDto,userId,categoryId));
    }

    @PutMapping("/{postId}")
    ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        return new ResponseEntity<>(this.postService.updatePost(postDto,postId),HttpStatus.OK );
    }

    @GetMapping("posts/{postId}")
    ResponseEntity<PostDto> getPostById(Integer postId){
        return new ResponseEntity<>(this.postService.getPostById(postId),HttpStatus.OK);
    }
    //Pagination Implemented
    @GetMapping("/posts")  //http://www.localhost:8080/api/posts?pageNumber=1&pageSize=2&sortBy=postId Url will be used to fetch the Posts in Pages
    ResponseEntity<PostResponse> getAllPosts(@RequestParam(value ="pageNumber",defaultValue ="10",required = false) Integer pageNumber,
                                             @RequestParam(value ="pageNumber",defaultValue ="1",required = false)Integer pageSize,
                                             @RequestParam(value ="sortBy",defaultValue ="postId",required = false)String sortBy){
        return new ResponseEntity<>(this.postService.getAllPosts(pageNumber,pageSize,sortBy),HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    ResponseEntity<?> deletePostById(@PathVariable("postId") Integer postId){
        this.postService.deletePostById(postId);
        return new ResponseEntity<>("Posts Deleted Successfully",HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("categoryId") Integer categoryId){
        return new ResponseEntity<>(this.postService.getPostsByCategory(categoryId),HttpStatus.OK);
    }
    @GetMapping("/user/{userId}/posts")
    ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Integer userId){
        return  new ResponseEntity<>(this.postService.getPostsByUser(userId),HttpStatus.OK);
    }
    @GetMapping("user/category/posts/{keywords}")
    ResponseEntity<List<PostDto>> getPostBySearch(@PathVariable("keywords") String keywords){
        return  new ResponseEntity<>(this.postService.getPostBySearch(keywords),HttpStatus.OK);
    }


}
