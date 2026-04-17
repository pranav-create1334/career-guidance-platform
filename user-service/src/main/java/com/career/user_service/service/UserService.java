package com.career.user_service.service;
import com.career.user_service.security.JwtUtil;
import com.career.user_service.dto.LoginRequest;
import com.career.user_service.dto.LoginResponse;
import com.career.user_service.dto.UserDTO;
import com.career.user_service.entity.User;
import com.career.user_service.exception.UserNotFoundException;
import com.career.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    // ✅ Register user
    public UserDTO registerUser(User user) {

        // 🔐 Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }


    // ✅ Get user by ID
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        return convertToDTO(user);
    }

    // ✅ Get all users
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // ✅ Convert Entity → DTO
    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    public User loginUser(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found with email: " + request.getEmail()
                ));

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new UserNotFoundException("Invalid password");
        }
    }
}
