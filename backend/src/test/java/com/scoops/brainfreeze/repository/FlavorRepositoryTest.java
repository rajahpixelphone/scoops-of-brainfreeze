package com.scoops.brainfreeze.repository;

import com.scoops.brainfreeze.model.Flavor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FlavorRepositoryTest {

    @Autowired
    private FlavorRepository flavorRepository;

    @BeforeEach
    void setUp() {
        Flavor chocolate = new Flavor();
        chocolate.setName("Chocolate Chip Cookie Dough");
        chocolate.setDescription("Classic favorite");
        chocolate.setPrice(120.0);
        flavorRepository.save(chocolate);

        Flavor vanilla = new Flavor();
        vanilla.setName("Vanilla Bean");
        vanilla.setDescription("Simple and pure");
        vanilla.setPrice(100.0);
        flavorRepository.save(vanilla);
    }

    @Test
    void searchByName_shouldReturnMatchingFlavors() {
        List<Flavor> results = flavorRepository.searchByNameVulnerable("Chocolate");
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getName()).contains("Chocolate");
    }

    // This test documents the vulnerable behavior for educational purposes
    @Test
    void searchByName_vulnerableToSqlInjection() {
        // This will return all records due to the vulnerable native query
        // In a real secure app this should be prevented
        List<Flavor> results = flavorRepository.searchByNameVulnerable("' OR '1'='1");
        // We expect the vulnerable query to return more than intended
        assertThat(results.size()).isGreaterThanOrEqualTo(2);
    }
}
