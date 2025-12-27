package com.example.demo.security;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomUserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loadUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

    public boolean validateUser(String email, String password) {
        User user = loadUserByEmail(email);
        return user != null && password.equals(user.getPassword());
    }
}