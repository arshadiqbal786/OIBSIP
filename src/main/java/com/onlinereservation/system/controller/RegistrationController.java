package com.onlinereservation.system.controller;

import com.onlinereservation.system.entity.User;
import com.onlinereservation.system.payload.UserDTO;
import com.onlinereservation.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// RegistrationController.java
@RestController
@RequestMapping("/api/auth")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        User registeredUser = userService.registerUser(userDTO);
        return ResponseEntity.ok("User registered successfully");
    }
}

