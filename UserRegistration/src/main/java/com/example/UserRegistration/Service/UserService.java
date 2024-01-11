package com.example.UserRegistration.Service;

import com.example.UserRegistration.Dto.UserDTO;
import com.example.UserRegistration.Entity.User;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String addUser(UserDTO userDTO);
    User login(String username, String password);
    User findByEmail(String email);
}
