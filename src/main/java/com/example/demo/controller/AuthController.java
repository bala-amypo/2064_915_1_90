package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.model.ApiResponse;
import com.example.demo.model.AuthRequest;
import com.example.demo.model.AuthResponse;


import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController{

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody User user) {
        return new ApiResponse();
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody AuthRequest request) throws Throwable {
        // Basic placeholder login logic (no JWT since pom is unchanged)
        User user = userService.getUserByEmail(request.getEmail());
        AuthResponse response = new AuthResponse();
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setToken("DUMMY_TOKEN");

        return new ApiResponse();
    }
}
