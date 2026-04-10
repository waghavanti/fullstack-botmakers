package com.botmakers.backend.dto;

import com.botmakers.backend.entity.Role;
import lombok.Data;

// DTO = Data Transfer Object
// This is what the frontend sends when registering
// We use a separate class so we don't expose the User entity directly
@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role; // USER or ADMIN
}