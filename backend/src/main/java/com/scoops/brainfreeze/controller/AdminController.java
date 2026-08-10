package com.scoops.brainfreeze.controller;

import com.scoops.brainfreeze.model.Order;
import com.scoops.brainfreeze.model.User;
import com.scoops.brainfreeze.repository.OrderRepository;
import com.scoops.brainfreeze.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Intentionally vulnerable Admin endpoints (A01 Broken Access Control).
 * No role checks – any caller can access admin data.
 */
@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public AdminController(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        // Vulnerable: no authentication or role check
        return userRepository.findAll();
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        // Vulnerable: no authentication or role check
        return orderRepository.findAll();
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userRepository.count());
        stats.put("totalOrders", orderRepository.count());
        stats.put("message", "Admin stats – no auth required (intentional vulnerability)");
        return stats;
    }
}
