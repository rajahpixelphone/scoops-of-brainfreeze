package com.scoops.brainfreeze.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long flavorId;

    private String authorName;

    // Intentionally not sanitized - for Stored XSS demo (A05)
    @Column(length = 2000)
    private String comment;

    private Integer rating; // 1-5

    private LocalDateTime createdAt = LocalDateTime.now();
}
