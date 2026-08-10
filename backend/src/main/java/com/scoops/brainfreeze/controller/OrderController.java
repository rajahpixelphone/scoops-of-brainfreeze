package com.scoops.brainfreeze.controller;

import com.scoops.brainfreeze.model.Order;
import com.scoops.brainfreeze.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Intentionally vulnerable - No ownership check (A01 Broken Access Control - IDOR)
    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        // Also vulnerable - returns all orders regardless of user
        return orderRepository.findAll();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        // A06 Insecure Design - allows negative quantity
        if (order.getQuantity() == null) {
            order.setQuantity(1);
        }
        // No validation on negative quantity → can result in negative total (credit)
        order.setTotalPrice(order.getQuantity() * 120.0); // Simplified pricing
        return orderRepository.save(order);
    }

    // Vulnerable update - no ownership check
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order updated) {
        Optional<Order> existing = orderRepository.findById(id);
        if (existing.isPresent()) {
            Order order = existing.get();
            order.setQuantity(updated.getQuantity());
            order.setNotes(updated.getNotes());
            order.setStatus(updated.getStatus());
            return orderRepository.save(order);
        }
        return null;
    }
}
