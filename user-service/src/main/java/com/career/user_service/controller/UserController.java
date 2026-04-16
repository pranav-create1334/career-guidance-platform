package com.career.user_service.controller;

import com.career.user_service.dto.LoginRequest;
import com.career.user_service.dto.LoginResponse;
import com.career.user_service.dto.UserDTO;
import com.career.user_service.entity.User;
import com.career.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Register User
    @PostMapping("/register")
    public UserDTO registerUser(@Valid @RequestBody User user) {
        return userService.registerUser(user);
    }

    // ✅ Get User by ID
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // ✅ Get All Users
    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
    // login:
    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody LoginRequest request) {
        return userService.loginUser(request);
    }
}
