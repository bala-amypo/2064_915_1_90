package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo){ this.repo=repo; }

    public User registerUser(User u){
        if(repo.existsByEmail(u.getEmail()))
            throw new RuntimeException("email exists");
        return repo.save(u);
    }

    public User getUser(Long id){
        return repo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

    public List<User> getAllUsers(){ return repo.findAll(); }
}