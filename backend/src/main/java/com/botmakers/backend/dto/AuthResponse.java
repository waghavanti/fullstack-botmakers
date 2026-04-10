package com.botmakers.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// This is what we send BACK to the frontend after login
// Just the JWT token and role
@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String role;
}