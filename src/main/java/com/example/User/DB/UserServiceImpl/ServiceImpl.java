package com.example.User.DB.UserServiceImpl;

import com.example.User.DB.DTO.UserDto;
import com.example.User.DB.Entity.User;
import com.example.User.DB.UserRepository.Repository;
import com.example.User.DB.UserService.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    @Autowired
    Repository Repo;
    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=this.Repo.findAll();
        List<UserDto> userDtos=users.stream().map(user -> this.userTODto(user)).collect(Collectors.toList());
    return  userDtos;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
      User user = this.dtoToUser(userDto);
        User saveduser=this. Repo.save(user);
        return this.userTODto(saveduser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int id) {
        User user = this.Repo.findById(id).orElseThrow();
        user.setUserName(userDto.getUserName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setConfirmPassword(userDto.getConfirmPassword());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        User updateUser=this.Repo.save(user);
        UserDto userDto1 =this.userTODto(updateUser);
        return userDto1;


    }

    @Override
    public void deleteUser(int id) {
        Repo.deleteById(id);

    }

    @Override
    public UserDto findUser(int id) {
    User user= this. Repo.findById(id).get();
    return this.userTODto(user);
    }

    public boolean authenticateUser(String email, String password) {
        Optional<User> userOptional = Repo.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Implement your logic for password validation here
            return user.getPassword().equals(password);
        } else {
            return false;
        }
    }
    private User dtoToUser(UserDto userDto){
        User user=new User();
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setConfirmPassword(new BCryptPasswordEncoder().encode(userDto.getConfirmPassword()));
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }
    public UserDto userTODto(User user){
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }



}