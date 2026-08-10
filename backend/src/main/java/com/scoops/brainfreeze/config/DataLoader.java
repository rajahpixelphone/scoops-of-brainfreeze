package com.scoops.brainfreeze.config;

import com.scoops.brainfreeze.model.Flavor;
import com.scoops.brainfreeze.model.User;
import com.scoops.brainfreeze.repository.FlavorRepository;
import com.scoops.brainfreeze.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final FlavorRepository flavorRepository;
    private final UserRepository userRepository;

    public DataLoader(FlavorRepository flavorRepository, UserRepository userRepository) {
        this.flavorRepository = flavorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (flavorRepository.count() == 0) {
            createFlavor("Chocolate Chip Cookie Dough", "Classic favorite with cookie chunks", 140.0);
            createFlavor("Vanilla Bean", "Pure and simple", 110.0);
            createFlavor("Strawberry Cheesecake", "Tangy and creamy", 150.0);
            createFlavor("Mint Chocolate Chip", "Cool and refreshing", 130.0);
            createFlavor("Butterscotch", "Rich and buttery", 125.0);
            createFlavor("Black Currant", "Slightly tart and unique", 145.0);
        }

        if (userRepository.count() == 0) {
            // Intentionally weak credentials for A02 / A07 demos
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("softserve123"); // Plain text for educational demo
            admin.setRole("ADMIN");
            admin.setFullName("Shop Owner");
            userRepository.save(admin);

            User customer = new User();
            customer.setUsername("student");
            customer.setPassword("password");
            customer.setRole("USER");
            customer.setFullName("CSE Student");
            userRepository.save(customer);
        }
    }

    private void createFlavor(String name, String description, Double price) {
        Flavor f = new Flavor();
        f.setName(name);
        f.setDescription(description);
        f.setPrice(price);
        f.setAvailable(true);
        flavorRepository.save(f);
    }
}
