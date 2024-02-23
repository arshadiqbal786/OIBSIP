package com.onlinereservation.system.controller;

import com.onlinereservation.system.entity.User;
import com.onlinereservation.system.payload.LoginDTO;
import com.onlinereservation.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// LoginController.java
@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        User user = userService.findByUsername(loginDTO.getUsername());
        if (user == null || !user.getPassword().equals(loginDTO.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        return ResponseEntity.ok("Login successful");
    }
}
