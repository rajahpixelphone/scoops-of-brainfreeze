package com.scoops.brainfreeze.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // Intentionally not using proper relation for simpler IDOR demo

    private Long flavorId;

    private Integer quantity;

    private String notes; // Potential injection / XSS point

    private Double totalPrice;

    private LocalDateTime createdAt = LocalDateTime.now();

    private String status = "PLACED";
}
