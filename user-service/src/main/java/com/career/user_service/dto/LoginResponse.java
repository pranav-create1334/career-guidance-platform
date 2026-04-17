package com.career.user_service.dto;

public class LoginResponse {

    private String message;
    private boolean success;
    private String token;
    private Long id;
    private String email;
    private String name;

    // Constructor for login response
    public LoginResponse(String token, Long id, String email, String name) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.name = name;
        this.success = true;
        this.message = "Login successful";
    }

    // Constructor for error response
    public LoginResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
