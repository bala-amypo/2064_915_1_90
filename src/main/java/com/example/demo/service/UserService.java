package com.example.demo.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.example.demo.model.User;

public interface UserService {

    User registerUser(User user);

    User getUser(Long id) throws NotFoundException;

    User getUserByEmail(String email) throws Throwable;

    List<User> getAllUsers();
}
