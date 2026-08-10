package com.scoops.brainfreeze.repository;

import com.scoops.brainfreeze.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFlavorId(Long flavorId);
}
