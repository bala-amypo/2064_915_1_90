package com.example.demo.security;

// DUMMY JWT UTIL – works without jjwt
public class JwtUtil {

    public String generateToken(String username) {
        return "DUMMY_TOKEN_" + username;
    }

    public String extractUsername(String token) {
        return "dummy-user";
    }

    public boolean isTokenValid(String token) {
        return true;
    }
}
