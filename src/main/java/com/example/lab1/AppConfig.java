package com.example.lab1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AppConfig {
    @Bean
    public Client client() {
        return new Client("Client x", "x@gmail.com");
    }

    @Bean
    public TeamMember teamMember1() {
        return TeamMember.builder()
                .name("Ana")
                .role("Engineer")
                .experienceYears(1)
                .build();
    }

    @Bean
    public TeamMember teamMember2() {
        return TeamMember.builder()
                .name("Victor")
                .role("Developer")
                .experienceYears(1)
                .build();
    }
}
