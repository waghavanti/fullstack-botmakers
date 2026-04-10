package com.botmakers.backend.controller;

import com.botmakers.backend.dto.AuthResponse;
import com.botmakers.backend.dto.LoginRequest;
import com.botmakers.backend.dto.RegisterRequest;
import com.botmakers.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// @RestController = this class handles HTTP requests and returns JSON
// @RequestMapping = all endpoints in this class start with /api/auth
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    // POST /api/auth/register
    // Frontend sends name, email, password, role
    // Returns JWT token + role
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    // POST /api/auth/login
    // Frontend sends email, password
    // Returns JWT token + role
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}