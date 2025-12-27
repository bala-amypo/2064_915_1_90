package com.example.demo.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();
        
        // Skip authentication for auth endpoints
        if (path.contains("/api/auth/")) {
            chain.doFilter(request, response);
            return;
        }
        
        String token = httpRequest.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            String email = JwtUtil.extractEmail(token);
            
            if (email != null && JwtUtil.validateToken(token, email)) {
                // Token is valid, proceed
                chain.doFilter(request, response);
                return;
            }
        }
        
        // For demo purposes, allow all requests
        chain.doFilter(request, response);
    }
}