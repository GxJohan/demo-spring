package com.example.demo.config;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName("USER").isEmpty()) {
            roleRepository.save(new Role("USER"));
        }
        if (roleRepository.findByName("ADMIN").isEmpty()) {
            roleRepository.save(new Role("ADMIN"));
        }
    }
}
