package com.scoops.brainfreeze.repository;

import com.scoops.brainfreeze.model.Flavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FlavorRepository extends JpaRepository<Flavor, Long> {

    // Intentionally vulnerable native query for A05 Injection demo
    @Query(value = "SELECT * FROM flavors WHERE name LIKE %:keyword%", nativeQuery = true)
    List<Flavor> searchByNameVulnerable(@Param("keyword") String keyword);
}
