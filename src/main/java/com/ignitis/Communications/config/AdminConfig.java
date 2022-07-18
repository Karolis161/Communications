package com.ignitis.Communications.config;

import com.ignitis.Communications.dto.User;
import com.ignitis.Communications.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AdminConfig {

    @Bean
    CommandLineRunner commandLineRunnerAdmin(AdminRepository repository){

        return args -> {
            User tom = new User(
                    "tom",
                    "tom123"
            );

            User john = new User(
                    "john",
                    "john123"
            );

            repository.saveAll(
                    List.of(tom, john)
            );
        };
    }
}
