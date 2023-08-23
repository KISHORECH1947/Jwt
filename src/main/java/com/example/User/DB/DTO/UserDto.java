package com.example.User.DB.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String email;
    private String phoneNumber;

}
