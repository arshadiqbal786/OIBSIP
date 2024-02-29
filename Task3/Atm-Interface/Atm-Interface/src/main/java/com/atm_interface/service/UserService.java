package com.atm_interface.service;

import com.atm_interface.entity.User;
import com.atm_interface.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public String authenticateUser(String userId, String pin) {
        // Find user by userId
        Optional<User> optionalUser = userRepository.findByUserId(userId);

        // Check if user exists and pin matches
        if (optionalUser.isPresent() && optionalUser.get().getPin().equals(pin)) {
            // Return authentication token upon successful authentication
            return "Welcome you can now access your account";
        } else {
            // Throw exception or return null for unsuccessful authentication
            throw new RuntimeException("Invalid user credentials");
        }
    }

    public void logoutUser(String userId) {
        // Perform logout logic here, e.g., invalidate the authentication token
        // Find user by userId (assuming UserRepository has a findByUserId method)
        Optional<User> optionalUser = userRepository.findByUserId(userId);

        if (optionalUser.isPresent()) {
            // If user is found, perform logout logic
            // For example, set some flag to indicate the user is logged out
            User user = optionalUser.get();
            user.setLoggedIn(false);
            userRepository.save(user);
        } else {
            // Handle case where user is not found (optional)
            // For example, log an error message or throw an exception
            throw new RuntimeException("User not found");
        }
    }
    public void registerUser(User user) {
        // Save the user to the database
        userRepository.save(user);
    }
}

