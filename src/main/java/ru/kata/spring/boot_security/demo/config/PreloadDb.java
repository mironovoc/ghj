package ru.kata.spring.boot_security.demo.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;


import java.util.HashSet;

@Configuration
public class PreloadDb {

    private static final Logger log = LoggerFactory.getLogger(PreloadDb.class);

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository,
                                   UserRepository userRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");

            log.info("Preloading " + roleRepository.save(roleAdmin));
            log.info("Preloading " + roleRepository.save(roleUser));

            log.info("Preloading " + userRepository.save(new User("Михаил", "Метелица", 33, "admin@mail.com",
                    passwordEncoder.encode("admin"),
                    new HashSet<>() {{
                        add(roleAdmin);
                        add(roleUser);
                    }})));
            log.info("Preloading " + userRepository.save(new User("Иван", "Иванов", 46, "user@mail.com",
                    passwordEncoder.encode("user"),
                    new HashSet<>() {{
                        add(roleUser);
                    }})));
        };
    }

}
