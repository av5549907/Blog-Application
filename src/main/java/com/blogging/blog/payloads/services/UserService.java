package com.blogging.blog.payloads.services;

import com.blogging.blog.payloads.UserDto;

import java.util.List;

public interface UserService {

    String startUserService();
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto ,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
