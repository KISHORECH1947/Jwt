package com.example.User.DB.UserService;

import com.example.User.DB.DTO.UserDto;
import com.example.User.DB.Entity.User;

import java.util.List;

public interface Service {
    public List<UserDto> getAllUsers();
    public UserDto saveUser(UserDto user);
    public UserDto updateUser(UserDto user,int id);
    public void deleteUser(int id);
    public  UserDto findUser(int id);
    boolean authenticateUser(String email, String password);

}
