package com.scoops.brainfreeze.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "flavors")
@Data
public class Flavor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Boolean available = true;
}
