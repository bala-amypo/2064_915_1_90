package com.example.demo.controller;

import com.example.demo.config.JwtUtil;
import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody AuthRequest request) {
        try {
            User user = new User("User", request.getEmail(), request.getPassword(), "USER");
            User savedUser = userService.registerUser(user);
            
            String token = JwtUtil.generateToken(savedUser.getEmail(), savedUser.getRole());
            AuthResponse authResponse = new AuthResponse(token, savedUser.getEmail(), savedUser.getRole(), savedUser.getId());
            
            return ResponseEntity.ok(ApiResponse.success(authResponse));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody AuthRequest request) {
        try {
            if (request.getEmail() == null || request.getPassword() == null) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Email and password required"));
            }
            
            String token = JwtUtil.generateToken(request.getEmail(), "USER");
            AuthResponse authResponse = new AuthResponse(token, request.getEmail(), "USER", 1L);
            
            return ResponseEntity.ok(ApiResponse.success(authResponse));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}