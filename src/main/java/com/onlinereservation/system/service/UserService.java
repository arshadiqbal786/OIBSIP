package com.onlinereservation.system.service;

import com.onlinereservation.system.entity.User;
import com.onlinereservation.system.payload.UserDTO;

public interface UserService {
    User findByUsername(String username);
    User registerUser(UserDTO userDTO);
}