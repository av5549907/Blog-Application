package com.blogging.blog.serviceImpl;

import com.blogging.blog.entities.User;
import com.blogging.blog.exception.*;
import com.blogging.blog.payloads.UserDto;
import com.blogging.blog.repository.UserRepo;
import com.blogging.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
     UserRepo userRepo;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public String startUserService() {
        return "Welcome to User Service";
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=dtoToUser(userDto);
        userRepo.save(user);
        UserDto newUserDto=UsertoUserDto(user);
        return newUserDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user= userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User Doesn't Exist","User","id",userId));
        User newUser=dtoToUser(userDto);
        newUser.setName(userDto.getName());
        newUser.setEmail(userDto.getEmail());
        newUser.setAbout(userDto.getAbout());
        newUser.setPassword(userDto.getPassword());
        newUser.setId(userDto.getId());
        userRepo.save(newUser);
        return UsertoUserDto(newUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
       User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("","User","id",userId));
       // User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User",false));
        return UsertoUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList=userRepo.findAll();
        List<UserDto> userDtoList=new ArrayList<>();
        for(User user:userList){
            UserDto userDto=UsertoUserDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("","User","Id",userId));
        userRepo.delete(user);

    }

    public User dtoToUser(UserDto userDto){
        User user=this.modelMapper.map(userDto, User.class);
        return  user;
    }

    public UserDto UsertoUserDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
        return userDto;
    }
}
