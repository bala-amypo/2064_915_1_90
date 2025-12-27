package com.example.demo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String SECRET = "mySecretKey";
    private static final long EXPIRATION = 86400000; // 24 hours

    public static String generateToken(String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return createToken(claims, email);
    }

    private static String createToken(Map<String, Object> claims, String subject) {
        return "jwt_" + subject + "_" + System.currentTimeMillis();
    }

    public static String extractEmail(String token) {
        String[] parts = token.split("_");
        return parts.length > 1 ? parts[1] : null;
    }

    public static boolean isTokenExpired(String token) {
        return false; // Simplified for minimal implementation
    }

    public static boolean validateToken(String token, String email) {
        return extractEmail(token).equals(email) && !isTokenExpired(token);
    }
}