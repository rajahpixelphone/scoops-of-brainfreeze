package com.scoops.brainfreeze.controller;

import com.scoops.brainfreeze.model.Flavor;
import com.scoops.brainfreeze.repository.FlavorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flavors")
@CrossOrigin(origins = "*") // Intentionally open for A02 demo
public class FlavorController {

    private final FlavorRepository flavorRepository;

    public FlavorController(FlavorRepository flavorRepository) {
        this.flavorRepository = flavorRepository;
    }

    @GetMapping
    public List<Flavor> getAllFlavors() {
        return flavorRepository.findAll();
    }

    // Intentionally vulnerable endpoint for A05 Injection demo
    @GetMapping("/search")
    public List<Flavor> searchFlavors(@RequestParam String q) {
        return flavorRepository.searchByNameVulnerable(q);
    }

    @GetMapping("/{id}")
    public Flavor getFlavor(@PathVariable Long id) {
        return flavorRepository.findById(id).orElse(null);
    }
}
