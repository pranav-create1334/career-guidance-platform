package com.career.user_service.controller;

import com.career.user_service.dto.LoginRequest;
import com.career.user_service.dto.LoginResponse;
import com.career.user_service.dto.UserDTO;
import com.career.user_service.entity.User;
import com.career.user_service.security.JwtUtil;
import com.career.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    private JwtUtil jwtUtil;
    private User user2;

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
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request) {

        // Step 1: Authenticate user
        LoginResponse user = userService.loginUser(request);

        // Step 2: Generate JWT token
        String token = jwtUtil.generateToken(user2.getEmail());

        // Step 3: Create response
        LoginResponse response = new LoginResponse(
                token,
                user2.getId(),
                user2.getEmail(),
                user2.getName()
        );

        // Step 4: Return response
        return ResponseEntity.ok(response);
    }

}
