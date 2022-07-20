package com.ignitis.communications;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com/ignitis/communications/config",
        "com/ignitis/communications/controller",
        "com/ignitis/communications/dto",
        "com/ignitis/communications/repository",
        "com/ignitis/communications/service"})
@OpenAPIDefinition
public class CommunicationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunicationsApplication.class, args);
    }
}
