package com.atm_interface.controller;

import com.atm_interface.entity.User;
import com.atm_interface.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> credentials) {
        String userId = credentials.get("userId");
        String pin = credentials.get("pin");

        String authToken = userService.authenticateUser(userId, pin);
        return ResponseEntity.ok(authToken);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @GetMapping
    public ResponseEntity<String> logoutUser(@RequestParam String userId) {
        userService.logoutUser(userId);
        return ResponseEntity.ok("Logged out successfully");
    }

}
