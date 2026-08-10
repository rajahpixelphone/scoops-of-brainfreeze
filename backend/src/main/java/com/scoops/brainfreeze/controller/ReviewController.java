package com.scoops.brainfreeze.controller;

import com.scoops.brainfreeze.model.Review;
import com.scoops.brainfreeze.repository.ReviewRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewRepository reviewRepository;

    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @GetMapping("/flavor/{flavorId}")
    public List<Review> getReviewsByFlavor(@PathVariable Long flavorId) {
        return reviewRepository.findByFlavorId(flavorId);
    }

    // Intentionally vulnerable - no input sanitization (Stored XSS - A05)
    // Comment is stored and later rendered as-is in the frontend
    @PostMapping
    public Review createReview(@RequestBody Review review) {
        // No HTML escaping or sanitization performed
        return reviewRepository.save(review);
    }
}
