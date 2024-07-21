package com.blogging.blog.serviceImpl;

import com.blogging.blog.entities.Category;
import com.blogging.blog.entities.Post;
import com.blogging.blog.entities.User;
import com.blogging.blog.exception.ResourceNotFoundException;
import com.blogging.blog.payloads.PostDto;
import com.blogging.blog.payloads.PostResponse;
import com.blogging.blog.repository.CategoryRepo;
import com.blogging.blog.repository.PostRepo;
import com.blogging.blog.repository.UserRepo;
import com.blogging.blog.payloads.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo postRepo;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public String start() {
        return "Post Service is Started";
    }

    @Override
    public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("","User","Id",userId));
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("","Category","Id",categoryId));
        Post post=dtoToPost(postDto);
        post.setUser(user);
        post.setCategory(category);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        this.postRepo.save(post);
        return PostToPostDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto,Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("","Post","Id",postId));
        post.setPostContent(postDto.getPostContent());
        post.setPostTitle(postDto.getPostTitle());
        post.setAddedDate(postDto.getAddedDate());
        post.setImageName(postDto.getImageName());
        post.setUser(postDto.getUser());
        post.setCategory(postDto.getCategory());
        return PostToPostDto(post);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("","Post","Id",postId));
        return PostToPostDto(post);
    }

    /* Pagination is Implemented */
    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize) {
        Pageable p=  PageRequest.of(pageNumber,pageSize);
        Page<Post> posts=this.postRepo.findAll(p);
        List<Post> allPosts=posts.getContent();
        PostResponse postResponse=new PostResponse();
        postResponse.setPostDtoList(allPosts.stream().map((post)->PostToPostDto(post)).collect(Collectors.toList()));
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElement(posts.getNumberOfElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLastPage(posts.isLast());

        return postResponse;
    }

    @Override
    public void deletePostById(Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("","Post","Id",postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("","Category","Id",categoryId));
        List<Post> posts=this.postRepo.findAllByCategory(category);
        return posts.stream().map((post)->PostToPostDto(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("","User","Id",userId));
        List<Post> posts=this.postRepo.findAllByUser(user);
        return posts.stream().map((post)->PostToPostDto(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostBySearch(String keywords) {
        List<Post> posts=this.postRepo.findAll();
        List<Post> keyPost= new ArrayList<>();
        for(Post post:posts){
            if(post.getPostTitle().contains(keywords))
                keyPost.add(post);
        }
        return keyPost.stream().map((post)->PostToPostDto(post)).collect(Collectors.toList());
    }

    Post dtoToPost(PostDto postDto){
        return this.modelMapper.map(postDto,Post.class);
    }

    PostDto PostToPostDto(Post post){
        return this.modelMapper.map(post,PostDto.class);
    }

}
