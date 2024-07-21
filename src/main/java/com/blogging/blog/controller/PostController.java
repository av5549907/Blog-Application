package com.blogging.blog.controller;

import com.blogging.blog.config.AppConfig;
import com.blogging.blog.payloads.PostDto;
import com.blogging.blog.payloads.PostResponse;
import com.blogging.blog.payloads.ResponseHandler;
import com.blogging.blog.services.FileService;
import com.blogging.blog.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    FileService fileService;
    @Value("${project.image}")
    String path;

    @GetMapping("/start")
    ResponseEntity<Object> start(){
        return ResponseHandler.responseBuilder("Post Service is Started", HttpStatus.OK,this.postService.start());
    }

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        return new ResponseEntity<>(this.postService.createPost(postDto,userId,categoryId),HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        return new ResponseEntity<>(this.postService.updatePost(postDto,postId),HttpStatus.OK );
    }

    @GetMapping("/posts/{postId}")
    ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        return new ResponseEntity<>(this.postService.getPostById(postId),HttpStatus.OK);
    }
    //Pagination Implemented
    @GetMapping("/posts")  //http://www.localhost:8080/api/posts?pageNumber=1&pageSize=2&sortBy=postId Url will be used to fetch the Posts in Pages
    ResponseEntity<PostResponse> getAllPosts(@RequestParam(value ="pageNumber",defaultValue = AppConfig.PAGE_NUMBER,required = AppConfig.PAGE_NUMBER_REQUIRED) Integer pageNumber,
                                             @RequestParam(value ="pageSize",defaultValue =AppConfig.PAGE_SIZE,required = AppConfig.PAGE_SIZE_REQUIRED)Integer pageSize,
                                             @RequestParam(value ="sortBy",defaultValue =AppConfig.SORT_BY,required = AppConfig.SORT_BY_REQUIRED)String sortBy){
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
    @GetMapping("/posts/search/{keywords}")
    ResponseEntity<List<PostDto>> getPostBySearch(@PathVariable("keywords") String keywords){
        return  new ResponseEntity<>(this.postService.getPostBySearch(keywords),HttpStatus.OK);
    }
    @PostMapping("/post/upload/image/{postId}")
   ResponseEntity<PostDto> uploadImage(@PathVariable Integer postId, @RequestParam("image")MultipartFile image) throws IOException {
        String filename=this.fileService.uploadImage(path,image);
        PostDto postDto=this.postService.getPostById(postId);
        postDto.setImageName(filename);
        PostDto updatedPost= this.postService.updatePost(postDto,postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }


}
