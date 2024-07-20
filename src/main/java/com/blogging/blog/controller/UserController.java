package com.blogging.blog.controller;

import com.blogging.blog.payloads.ApiResponse;
import com.blogging.blog.payloads.UserDto;
import com.blogging.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/start")
    public ResponseEntity<?> startUserService(){
        String message= this.userService.startUserService();
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }

    @PostMapping("/")
    public  ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto user=this.userService.createUser(userDto);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }
    @PutMapping("user/{userid}")
    public  ResponseEntity<UserDto>  updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId){
        UserDto user=this.userService.updateUser(userDto,userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<List<UserDto>>(this.userService.getAllUsers(),HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId){
        return new ResponseEntity<>(this.userService.getUserById(userId),HttpStatus.OK);
    }
    @DeleteMapping("/{userId}")
    public  ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    }
}
