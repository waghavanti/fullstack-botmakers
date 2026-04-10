package com.botmakers.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// This controller has 3 endpoints
// Each one returns different content based on who is allowed
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ContentController {

    // Anyone can access this - no token needed
    @GetMapping("/public")
    public ResponseEntity<String> publicContent() {
        return ResponseEntity.ok("This is PUBLIC content - anyone can see this!");
    }

    // Only users with ROLE_USER can access this
    @GetMapping("/user")
    public ResponseEntity<String> userContent() {
        return ResponseEntity.ok("This is USER content - only logged in users can see this!");
    }

    // Only users with ROLE_ADMIN can access this
    @GetMapping("/admin")
    public ResponseEntity<String> adminContent() {
        return ResponseEntity.ok("This is ADMIN content - only admins can see this!");
    }
}