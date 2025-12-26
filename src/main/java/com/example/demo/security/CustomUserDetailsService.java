package com.example.demo.security;

// DUMMY CLASS – no spring security required
public class CustomUserDetailsService {

    public Object loadUserByUsername(String email) {
        // Just dummy method to avoid compile error
        System.out.println("Security Disabled - No UserDetailsService used");
        return null;
    }
}
