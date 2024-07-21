package com.blogging.blog.services;

import com.blogging.blog.entities.Category;
import com.blogging.blog.entities.User;
import com.blogging.blog.payloads.PostDto;

import java.util.List;

public interface PostService {

    String start();
    PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto,Integer postId);
    PostDto getPostById(Integer postId);
    List<PostDto> getAllPosts();
    void deletePostById(Integer postId);
    List<PostDto> getPostsByCategory(Integer categoryId);
    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> getPostBySearch(String keywords);

}
