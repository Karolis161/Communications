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
    CommandLineRunner commandLineRunnerUser(UserRepository repository){

        return args -> {
            Message abc = new Message(
                    "tom",
                    LocalDateTime.now(),
                    "Hello"
            );

            Message cba = new Message(
                    "john",
                    LocalDateTime.now(),
                    "Welcome"
            );

            repository.saveAll(
                    List.of(abc, cba)
            );
        };
    }
}
