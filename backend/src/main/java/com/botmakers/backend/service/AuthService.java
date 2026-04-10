package com.botmakers.backend.service;

import com.botmakers.backend.dto.AuthResponse;
import com.botmakers.backend.dto.LoginRequest;
import com.botmakers.backend.dto.RegisterRequest;
import com.botmakers.backend.entity.Role;
import com.botmakers.backend.entity.User;
import com.botmakers.backend.repository.UserRepository;
import com.botmakers.backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    // REGISTER - saves new user to database
    public AuthResponse register(RegisterRequest request) {

        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        // Build new user
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // encrypt password
                .role(request.getRole() != null ? request.getRole() : Role.USER) // default USER
                .build();

        // Save user
        userRepository.save(user);

        // Generate JWT token
       String token = jwtUtil.generateToken(
        user.getEmail(),
        user.getRole().name()
);

        // Return response
        return new AuthResponse(token, user.getRole().name());
    }

    // LOGIN - checks credentials and returns token
    public AuthResponse login(LoginRequest request) {

        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Fetch user from DB
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate JWT token
       String token = jwtUtil.generateToken(
        user.getEmail(),
        user.getRole().name()
);
        // Return response
        return new AuthResponse(token, user.getRole().name());
    }
}