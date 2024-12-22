package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Создаем роли, если их нет
        Role adminRole = roleRepository.save(new Role(1L, "ROLE_ADMIN"));
        Role userRole = roleRepository.save(new Role(2L, "ROLE_USER"));

        // Создаем пользователя admin
        User admin = new User();
        admin.setUsername("admin");
        admin.setAge((byte) 33); // Устанавливаем возраст
        admin.setFirstname("Oleg"); // Устанавливаем имя
        admin.setLastname("Adminov"); // Устанавливаем фамилию
        admin.setEmail("admin@example.com");
        admin.setPassword(passwordEncoder.encode("admin"));

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminRoles.add(userRole);
        admin.setRoles(adminRoles);
        userRepository.save(admin);

        // Создаем пользователя user
        User user = new User();
        user.setUsername("user");
        user.setAge((byte) 25); // Устанавливаем возраст
        user.setFirstname("Ivan"); // Устанавливаем имя
        user.setLastname("Userov"); // Устанавливаем фамилию
        user.setEmail("user@example.com");
        user.setPassword(passwordEncoder.encode("user"));

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRoles(userRoles);
        userRepository.save(user);
    }
}