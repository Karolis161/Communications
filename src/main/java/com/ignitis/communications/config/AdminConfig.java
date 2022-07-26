package com.ignitis.communications.config;

import com.ignitis.communications.dto.User;
import com.ignitis.communications.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AdminConfig {

    @Bean
    CommandLineRunner commandLineRunnerAdmin(AdminRepository repository) {

        return args -> {
            User tom = new User(
                    "tom",
                    "tom123"
            );

            User john = new User(
                    "john",
                    "john123"
            );

            User dan = new User(
                    "dan",
                    "dan123"
            );

            User james = new User(
                    "james",
                    "james123"
            );

            repository.saveAll(
                    List.of(tom, john, dan, james)
            );
        };
    }
}
