package com.botmakers.backend.dto;

import lombok.Data;

// This is what the frontend sends when logging in
@Data
public class LoginRequest {
    private String email;
    private String password;
}