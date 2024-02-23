package com.onlinereservation.system.entity;

import javax.persistence.*;
import lombok.Data;

// User.java
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;


}
