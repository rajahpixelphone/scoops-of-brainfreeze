package com.scoops.brainfreeze.controller;

import com.scoops.brainfreeze.model.User;
import com.scoops.brainfreeze.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Intentionally weak authentication (A07 Authentication Failures)
    // - No rate limiting
    // - Plain text password comparison
    // - Returns role information that can be useful for further attacks
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Optional<User> userOpt = userRepository.findByUsername(username);

        Map<String, Object> response = new HashMap<>();

        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            User user = userOpt.get();
            response.put("success", true);
            response.put("username", user.getUsername());
            response.put("role", user.getRole());
            response.put("fullName", user.getFullName());
            response.put("userId", user.getId());
            // In a real app we would return a proper JWT. Here we keep it simple for demos.
            response.put("token", "fake-jwt-token-for-demo-" + user.getId());
        } else {
            response.put("success", false);
            response.put("message", "Invalid credentials");
        }

        return response;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User newUser) {
        Map<String, Object> response = new HashMap<>();

        if (userRepository.findByUsername(newUser.getUsername()).isPresent()) {
            response.put("success", false);
            response.put("message", "Username already exists");
            return response;
        }

        // Intentionally storing password in plain text for A04 demo
        newUser.setRole("USER");
        userRepository.save(newUser);

        response.put("success", true);
        response.put("message", "User registered successfully");
        return response;
    }
}
