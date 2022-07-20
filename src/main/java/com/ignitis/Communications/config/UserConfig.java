package com.ignitis.Communications.config;

import com.ignitis.Communications.dto.Message;
import com.ignitis.Communications.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepository userRepository) {

        return args -> {
            Message tom = new Message(
                    "tom",
                    LocalDateTime.now(),
                    "Hello"
            );

            Message john = new Message(
                    "john",
                    LocalDateTime.now(),
                    "Welcome"
            );

            Message dan = new Message(
                    "dan",
                    LocalDateTime.now(),
                    "How are you?"
            );

            Message james = new Message(
                    "james",
                    LocalDateTime.now(),
                    "Where are you?"
            );

            Message dan1 = new Message(
                    "dan",
                    LocalDateTime.now(),
                    "What's up?"
            );

            Message dan2 = new Message(
                    "dan",
                    LocalDateTime.now(),
                    "HEYYYY"
            );

            userRepository.saveAll(
                    List.of(tom, john, dan, james, dan1, dan2)
            );
        };
    }
}
