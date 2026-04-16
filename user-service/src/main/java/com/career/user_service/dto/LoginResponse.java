package com.career.user_service.dto;

public class LoginResponse {

    private String message;
    private boolean success;
    private String token;
    public LoginResponse(String message, boolean success, String token) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
