package com.ignitis.Communications.config;

import com.ignitis.Communications.dto.User;
import com.ignitis.Communications.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            User tom = new User(
                    "aaa",
                    "aaa123",
                    "Tom",
                    "Tommy"
            );

            User john = new User(
                    "bbb",
                    "bbb123",
                    "John",
                    "Johnny"
            );

            repository.saveAll(
                    List.of(tom, john)
            );
        };
    }
}
