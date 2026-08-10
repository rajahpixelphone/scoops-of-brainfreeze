package com.scoops.brainfreeze.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    // Intentionally weak - for A04 Cryptographic Failures demo
    private String password; // Will store MD5 or plain for educational purposes

    private String role = "USER"; // USER or ADMIN

    private String fullName;
}
