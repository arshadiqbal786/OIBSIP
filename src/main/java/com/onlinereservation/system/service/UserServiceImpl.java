package com.onlinereservation.system.service;

import com.onlinereservation.system.entity.User;
import com.onlinereservation.system.exception.UserAlreadyExistsException;
import com.onlinereservation.system.payload.UserDTO;
import com.onlinereservation.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public User registerUser(UserDTO userDTO) {
        User existingUser = userRepository.findByUsername(userDTO.getUsername());
        if (existingUser != null) {
            throw new UserAlreadyExistsException("Username already exists: " + userDTO.getUsername());
        }

        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());

        return userRepository.save(newUser);
    }
}