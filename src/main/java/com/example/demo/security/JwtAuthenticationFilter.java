package com.example.demo.security;

// DUMMY FILTER – NO SPRING SECURITY
public class JwtAuthenticationFilter {

    public void doFilter() {
        System.out.println("Security Disabled - No JWT Filter running");
    }
}
